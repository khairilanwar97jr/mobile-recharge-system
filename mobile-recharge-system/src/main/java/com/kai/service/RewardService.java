package com.kai.service;

import com.kai.dto.RewardResponse;
import com.kai.entity.*;
import com.kai.repository.*;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepo;

    @Autowired
    private RewardRedemptionRepository redemptionRepo;

    @Autowired
    private LoyaltyAccountRepository loyaltyRepo;

    @Autowired
    private LoyaltyTransactionRepository loyaltyTxRepo;

    @Autowired
    private UserRepository userRepo;

    // redeem reward
    @Transactional
    public RewardRedemption redeemReward(Long userId, Long rewardId) {

        // 1. Get user
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Get reward
        Reward reward = rewardRepo.findById(rewardId)
                .orElseThrow(() -> new RuntimeException("Reward not found"));

        // 3. Get loyalty account
        LoyaltyAccount account = loyaltyRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Loyalty account not found"));

        // 4. Check points
        if (account.getCurrentPoints() < reward.getRequiredPoints()) {
            throw new RuntimeException("Not enough points");
        }

        // 5. Check stock
        if (reward.getStock() <= 0) {
            throw new RuntimeException("Reward out of stock");
        }

        // 6. Deduct points
        account.setCurrentPoints(
                account.getCurrentPoints() - reward.getRequiredPoints()
        );
        loyaltyRepo.save(account);

        // 7. Reduce reward stock
        reward.setStock(reward.getStock() - 1);
        rewardRepo.save(reward);

        // 8. Save redemption record
        RewardRedemption redemption = new RewardRedemption();
        redemption.setUser(user);
        redemption.setReward(reward);
        redemption.setPointsUsed(reward.getRequiredPoints());
        redemption.setStatus("APPROVED");

        redemptionRepo.save(redemption);

        // 9. Save loyalty transaction (audit trail)
        LoyaltyTransaction tx = new LoyaltyTransaction();
        tx.setUser(user);
        tx.setTransactionType("REDEEM");
        tx.setPoints(reward.getRequiredPoints());
        tx.setDescription("Redeemed " + reward.getRewardName());

        loyaltyTxRepo.save(tx);

        return redemption;
    }
    
    public List<RewardResponse> getAllRewards() {

        List<Reward> rewards = rewardRepo.findAll();

        List<RewardResponse> response = new ArrayList<>();

        for (Reward reward : rewards) {

            RewardResponse dto = new RewardResponse();

            dto.setId(reward.getId());
            dto.setRewardName(reward.getRewardName());
            dto.setPointsRequired(reward.getRequiredPoints());

            response.add(dto);
        }

        return response;
    }
}