package com.kai.controller;

import com.kai.dto.RewardRequest;
import com.kai.entity.RewardRedemption;
import com.kai.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @PostMapping("/redeem")
    public ResponseEntity<RewardRedemption> redeemReward(
            @RequestBody RewardRequest request) {

        RewardRedemption redemption =
                rewardService.redeemReward(
                        request.getUserId(),
                        request.getRewardId());

        return ResponseEntity.ok(redemption);
    }
}