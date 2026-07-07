package com.kai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kai.dto.AdminRewardRequest;
import com.kai.entity.Reward;
import com.kai.service.AdminRewardService;

@RestController
@RequestMapping("/api/admin/rewards")
public class AdminRewardController {

    @Autowired
    private AdminRewardService adminRewardService;

    @PostMapping
    public ResponseEntity<Reward> createReward(
            @RequestBody AdminRewardRequest request) {

        return ResponseEntity.ok(
                adminRewardService.createReward(request));
    }

    @GetMapping
    public ResponseEntity<List<Reward>> getAllRewards() {

        return ResponseEntity.ok(
                adminRewardService.getAllRewards());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(
            @PathVariable Long id,
            @RequestBody AdminRewardRequest request) {

        return ResponseEntity.ok(
                adminRewardService.updateReward(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReward(
            @PathVariable Long id) {

        adminRewardService.deleteReward(id);

        return ResponseEntity.ok("Reward deleted successfully");
    }
}