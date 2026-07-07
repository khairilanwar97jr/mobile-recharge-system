package com.kai.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RechargeHistoryDto {

    private Long id;
    private String phoneNumber;
    private String packageName;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;

    public RechargeHistoryDto() {
    }

    public RechargeHistoryDto(Long id,
                              String phoneNumber,
                              String packageName,
                              BigDecimal amount,
                              String status,
                              LocalDateTime createdAt) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.packageName = packageName;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}