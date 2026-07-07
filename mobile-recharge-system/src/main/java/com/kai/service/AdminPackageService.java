package com.kai.service;

import java.util.List;

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
    
    
    public List<RechargePackage> getAllPackages() {

        return packageRepo.findAll();

    }
    
    public RechargePackage updatePackage(Long id, RechargePackageRequest request) {

        RechargePackage pkg = packageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        MobileOperator operator =
                operatorRepo.findById(request.getOperatorId())
                .orElseThrow(() -> new RuntimeException("Operator not found"));


        pkg.setPackageName(request.getPackageName());
        pkg.setAmount(request.getAmount());
        pkg.setPointsReward(request.getPointsReward());
        pkg.setOperator(operator);


        return packageRepo.save(pkg);
    }
    
    public void deletePackage(Long id) {

        RechargePackage pkg = packageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        packageRepo.delete(pkg);
    }
}