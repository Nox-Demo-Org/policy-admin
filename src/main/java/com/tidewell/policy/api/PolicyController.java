package com.tidewell.policy.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/policies")
public class PolicyController {

    /** GET /v1/policies/{id}: the policy with its cover lines. */
    @GetMapping("/{id}")
    public PolicyView get(@PathVariable String id) { return null; }

    /** POST /v1/policies: issue a policy after purchase; publishes policy.policy.issued. */
    @PostMapping
    public PolicyView issue(@RequestBody IssueRequest req) { return null; }

    /** POST /v1/policies/{id}/changes: mid-term change, re-priced through RatingService.Price. */
    @PostMapping("/{id}/changes")
    public PolicyView change(@PathVariable String id, @RequestBody ChangeRequest req) { return null; }

    public record PolicyView(String id, String customerId, String product, String status, String startDate,
                             String renewalDate, long annualPremiumPence, java.util.List<CoverLine> cover) {}
    public record CoverLine(String peril, long limitPence, long excessPence) {}
    public record IssueRequest(String customerId, String product, String quoteId) {}
    public record ChangeRequest(String type, String effectiveDate, java.util.Map<String, String> details) {}
}
