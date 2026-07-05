package com.kai.service;

import com.kai.entity.*;
import com.kai.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RechargeService {

    @Autowired
    private RechargeTransactionRepository transactionRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RechargePackageRepository packageRepo;

    @Autowired
    private LoyaltyAccountRepository loyaltyRepo;

    @Autowired
    private LoyaltyTransactionRepository loyaltyTxRepo;

    // MAIN FUNCTION: process recharge
    public RechargeTransaction processRecharge(Long userId, Long packageId, String phoneNumber) {

        // 1. Get user
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Get package
        RechargePackage pkg = packageRepo.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        // 3. Create transaction
        RechargeTransaction tx = new RechargeTransaction();
        tx.setUser(user);
        tx.setRechargePackage(pkg);
        tx.setPhoneNumber(phoneNumber);
        tx.setAmount(pkg.getAmount());
        tx.setStatus("SUCCESS");

        transactionRepo.save(tx);

        // 4. Update loyalty account
        LoyaltyAccount account = loyaltyRepo.findByUser(user)
                .orElseGet(() -> {
                    LoyaltyAccount newAcc = new LoyaltyAccount();
                    newAcc.setUser(user);
                    newAcc.setCurrentPoints(0);
                    return newAcc;
                });

        int updatedPoints = account.getCurrentPoints() + pkg.getPointsReward();
        account.setCurrentPoints(updatedPoints);

        loyaltyRepo.save(account);

        // 5. Save loyalty transaction (audit trail)
        LoyaltyTransaction loyaltyTx = new LoyaltyTransaction();
        loyaltyTx.setUser(user);
        loyaltyTx.setTransactionType("EARN");
        loyaltyTx.setPoints(pkg.getPointsReward());
        loyaltyTx.setDescription("Recharge " + pkg.getPackageName());

        loyaltyTxRepo.save(loyaltyTx);

        return tx;
    }
}