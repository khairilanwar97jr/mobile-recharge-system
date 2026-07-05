package com.kai.controller;

import com.kai.dto.RechargeRequest;
import com.kai.entity.RechargeTransaction;
import com.kai.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recharge")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @PostMapping
    public ResponseEntity<RechargeTransaction> recharge(
            @RequestBody RechargeRequest request) {

        RechargeTransaction transaction =
                rechargeService.processRecharge(
                        request.getUserId(),
                        request.getPackageId(),
                        request.getPhoneNumber());

        return ResponseEntity.ok(transaction);
    }
}