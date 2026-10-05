CREATE TABLE policies (
  id            UUID PRIMARY KEY,
  customer_id   UUID NOT NULL,
  product       TEXT NOT NULL,             -- home | motor
  status        TEXT NOT NULL,             -- active | lapsed | cancelled
  start_date    DATE NOT NULL,
  renewal_date  DATE NOT NULL,
  annual_premium_pence BIGINT NOT NULL
);

CREATE TABLE policy_cover (
  policy_id     UUID REFERENCES policies(id),
  peril         TEXT NOT NULL,             -- fire, flood, theft, escape_of_water, accidental_damage, ...
  limit_pence   BIGINT NOT NULL,
  excess_pence  BIGINT NOT NULL,
  active_from   DATE NOT NULL,
  active_to     DATE
);
