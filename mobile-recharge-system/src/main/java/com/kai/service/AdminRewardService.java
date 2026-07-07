package com.kai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kai.dto.AdminRewardRequest;
import com.kai.entity.Reward;
import com.kai.repository.RewardRepository;

@Service
public class AdminRewardService {

    @Autowired
    private RewardRepository rewardRepo;

    // Create Reward
    public Reward createReward(AdminRewardRequest request) {

        Reward reward = new Reward();

        reward.setRewardName(request.getRewardName());
        reward.setRequiredPoints(request.getRequiredPoints());
        reward.setStock(request.getStock());

        return rewardRepo.save(reward);
    }

    // Get All Rewards
    public List<Reward> getAllRewards() {

        return rewardRepo.findAll();
    }

    // Update Reward
    public Reward updateReward(Long id, AdminRewardRequest request) {

        Reward reward = rewardRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found"));

        reward.setRewardName(request.getRewardName());
        reward.setRequiredPoints(request.getRequiredPoints());
        reward.setStock(request.getStock());

        return rewardRepo.save(reward);
    }

    // Delete Reward
    public void deleteReward(Long id) {

        Reward reward = rewardRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found"));

        rewardRepo.delete(reward);
    }

}