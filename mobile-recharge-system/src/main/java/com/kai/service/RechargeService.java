package com.kai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kai.dto.RechargeHistoryDto;
import com.kai.entity.LoyaltyAccount;
import com.kai.entity.LoyaltyTransaction;
import com.kai.entity.RechargePackage;
import com.kai.entity.RechargeTransaction;
import com.kai.entity.User;
import com.kai.enums.TransactionStatus;
import com.kai.repository.LoyaltyAccountRepository;
import com.kai.repository.LoyaltyTransactionRepository;
import com.kai.repository.RechargePackageRepository;
import com.kai.repository.RechargeTransactionRepository;
import com.kai.repository.UserRepository;

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
    @Transactional
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
        tx.setStatus(TransactionStatus.SUCCESS);

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
    
    public List<RechargeHistoryDto> getRechargeHistory(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<RechargeTransaction> transactions = transactionRepo.findByUser(user);

        List<RechargeHistoryDto> history = new ArrayList<>();

        for (RechargeTransaction tx : transactions) {

            RechargeHistoryDto dto = new RechargeHistoryDto();

            dto.setId(tx.getId());
            dto.setPhoneNumber(tx.getPhoneNumber());
            dto.setPackageName(tx.getRechargePackage().getPackageName());
            dto.setAmount(tx.getAmount());
            dto.setStatus(tx.getStatus().name());
            dto.setCreatedAt(tx.getCreatedAt());

            history.add(dto);
        }

        return history;
    }
    
}