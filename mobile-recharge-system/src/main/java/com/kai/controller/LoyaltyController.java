package com.kai.controller;


import com.kai.dto.LoyaltyPointsDto;
import com.kai.service.LoyaltyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/loyalty")
public class LoyaltyController {


    private final LoyaltyService loyaltyService;


    public LoyaltyController(
            LoyaltyService loyaltyService
    ){
        this.loyaltyService = loyaltyService;
    }



    @GetMapping("/points/{userId}")
    public ResponseEntity<LoyaltyPointsDto> getPoints(
            @PathVariable Long userId
    ){

        return ResponseEntity.ok(
                loyaltyService.getPoints(userId)
        );

    }

}