package com.kai.service;

import com.kai.dto.LoyaltyPointsDto;
import com.kai.entity.LoyaltyAccount;
import com.kai.entity.User;
import com.kai.repository.LoyaltyAccountRepository;
import com.kai.repository.UserRepository;

import org.springframework.stereotype.Service;


@Service
public class LoyaltyService {


    private final LoyaltyAccountRepository loyaltyAccountRepository;
    private final UserRepository userRepository;


    public LoyaltyService(
            LoyaltyAccountRepository loyaltyAccountRepository,
            UserRepository userRepository
    ){
        this.loyaltyAccountRepository = loyaltyAccountRepository;
        this.userRepository = userRepository;
    }



    public LoyaltyPointsDto getPoints(Long userId){


        User user = userRepository
                .findById(userId)
                .orElseThrow();


        LoyaltyAccount account =
                loyaltyAccountRepository
                .findByUser(user)
                .orElseThrow();


        return new LoyaltyPointsDto(
                userId,
                account.getCurrentPoints()
        );

    }

}