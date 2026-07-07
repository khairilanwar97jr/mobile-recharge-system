package com.kai.controller;

import com.kai.dto.RewardRequest;
import com.kai.entity.RewardRedemption;
import com.kai.entity.User;
import com.kai.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @PostMapping("/redeem")
    public ResponseEntity<RewardRedemption> redeemReward(
            @RequestBody RewardRequest request,
            Authentication authentication) {


        User user = (User) authentication.getPrincipal();


        RewardRedemption redemption =
                rewardService.redeemReward(
                        user.getId(),
                        request.getRewardId()
                );


        return ResponseEntity.ok(redemption);
    }
}