package com.tidewell.policy.renewal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

public class RenewalScheduler {
    @Value("${renewal.notice-days}")
    private int noticeDays;   // 21

    /** Every night: policies renewing in exactly noticeDays get policy.renewal.due. */
    @Scheduled(cron = "0 0 2 * * *")
    public void publishDueRenewals() {
        // SELECT id FROM policies WHERE renewal_date = current_date + :noticeDays AND status = 'active'
        // -> publish policy.renewal.due {policy_id, customer_id, renewal_date, new_premium_pence}
    }
}
