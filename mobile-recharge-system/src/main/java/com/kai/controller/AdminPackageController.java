package com.kai.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kai.dto.RechargePackageRequest;
import com.kai.entity.RechargePackage;
import com.kai.service.AdminPackageService;



@RestController
@RequestMapping("/api/admin/packages")
public class AdminPackageController {


    @Autowired
    private AdminPackageService service;



    @PostMapping
    public ResponseEntity<RechargePackage> create(
            @RequestBody RechargePackageRequest request) {


        return ResponseEntity.ok(
                service.createPackage(request)
        );
    }
    
    @GetMapping
    public ResponseEntity<List<RechargePackage>> getAll() {

        return ResponseEntity.ok(
                service.getAllPackages()
        );
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RechargePackage> update(
            @PathVariable Long id,
            @RequestBody RechargePackageRequest request) {

        return ResponseEntity.ok(
                service.updatePackage(id, request)
        );
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        service.deletePackage(id);

        return ResponseEntity.ok("Package deleted successfully");
    }

}