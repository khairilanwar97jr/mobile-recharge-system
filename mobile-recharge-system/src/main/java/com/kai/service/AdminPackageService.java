package com.kai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kai.dto.RechargePackageRequest;
import com.kai.entity.MobileOperator;
import com.kai.entity.RechargePackage;
import com.kai.repository.MobileOperatorRepository;
import com.kai.repository.RechargePackageRepository;


@Service
public class AdminPackageService {


    @Autowired
    private RechargePackageRepository packageRepo;


    @Autowired
    private MobileOperatorRepository operatorRepo;



    public RechargePackage createPackage(RechargePackageRequest request) {


        MobileOperator operator =
                operatorRepo.findById(request.getOperatorId())
                .orElseThrow(() -> 
                    new RuntimeException("Operator not found"));


        RechargePackage pkg = new RechargePackage();

        pkg.setPackageName(request.getPackageName());
        pkg.setAmount(request.getAmount());
        pkg.setPointsReward(request.getPointsReward());
        pkg.setOperator(operator);


        return packageRepo.save(pkg);
    }
}