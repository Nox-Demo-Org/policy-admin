package com.tidewell.policy.events;

/** Payloads published by policy-admin. Schemas mirror docs in Confluence TWPOL "Policy events". */
public final class PolicyEvents {
    public static final String ISSUED = "policy.policy.issued";
    public static final String RENEWAL_DUE = "policy.renewal.due";
    public static final String CANCELLED = "policy.policy.cancelled";

    public record Issued(String policyId, String customerId, String product, String startDate,
                         long annualPremiumPence, String paymentFrequency,
                         String salesChannel /* web | broker | phone */) {}
    public record RenewalDue(String policyId, String customerId, String renewalDate, long newPremiumPence) {}
    public record Cancelled(String policyId, String cancelledOn, String reason) {}

    private PolicyEvents() {}
}
