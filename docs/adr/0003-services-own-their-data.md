# ADR-0003: Each service owns its data

Status: Accepted (2022-09)

The `policies` and `policy_cover` tables belong to policy-admin. Other services read policy data through `GET /v1/policies/{id}` or the policy events, never from the tables. This lets us change the schema without breaking anyone. Read access for other services' database users was removed in 2022.
