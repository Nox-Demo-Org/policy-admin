# policy-admin

The policy record for Tidewell Mutual: issue, change, renew and cancel home and motor policies. Most other services start from here.

Owned by **Policy / Policy Admin squad**. On-call: `#tw-architecture`.

## Contracts it owns

| Contract | Kind | Consumers |
| --- | --- | --- |
| `GET /v1/policies/{id}` | REST | customer-portal, claims-intake, billing-service |
| `POST /v1/policies` | REST | issue a policy after purchase |
| `POST /v1/policies/{id}/changes` | REST | mid-term changes (address, car, cover). Not offered in customer-portal yet. |
| `policy.policy.issued` | Event | billing-service, customer-identity |
| `policy.renewal.due` | Event | notifications-hub, billing-service |
| `policy.policy.cancelled` | Event | billing-service, claims-management |

## Contracts it uses

`gRPC RatingService.Price` (rating-service), `POST /v1/messages` (notifications-hub), and the events `customer.profile.updated` and `billing.payment.missed`.

## Data

Tables `policies` and `policy_cover` (see `src/main/resources/db/schema.sql`). Other services must use the API, never the tables ([ADR-0003](docs/adr/0003-services-own-their-data.md)).

## Renewals

`RenewalScheduler` publishes `policy.renewal.due` `renewal.notice-days` before the renewal date (21 days, `application.yml`).
