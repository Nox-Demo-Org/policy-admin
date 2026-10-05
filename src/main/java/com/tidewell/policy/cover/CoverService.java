package com.tidewell.policy.cover;

import java.util.ArrayList;
import java.util.List;

public class CoverService {
    private final CoverRepository covers;
    private final PolicyRepository policies;

    public CoverService(CoverRepository covers, PolicyRepository policies) {
        this.covers = covers;
        this.policies = policies;
    }

    /**
     * All cover lines for a customer's policies. Used by GET /v1/policies?customerId=.
     * One query for the policies, then one query per policy for its cover.
     */
    public List<Object> coverForCustomer(String customerId) {
        List<Object> out = new ArrayList<>();
        for (var policy : policies.findByCustomerId(customerId)) {
            out.addAll(covers.findByPolicyId(policy.id()));   // query per policy
        }
        return out;
    }

    interface CoverRepository { List<Object> findByPolicyId(String policyId); }
    interface PolicyRepository { List<PolicyRow> findByCustomerId(String customerId); }
    record PolicyRow(String id) {}
}
