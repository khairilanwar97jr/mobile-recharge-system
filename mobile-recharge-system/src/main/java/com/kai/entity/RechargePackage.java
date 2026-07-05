package com.kai.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "recharge_packages")
public class RechargePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String packageName;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Integer pointsReward;

    @ManyToOne
    @JoinColumn(name = "operator_id", nullable = false)
    private MobileOperator operator;

    public RechargePackage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getPointsReward() {
        return pointsReward;
    }

    public void setPointsReward(Integer pointsReward) {
        this.pointsReward = pointsReward;
    }

    public MobileOperator getOperator() {
        return operator;
    }

    public void setOperator(MobileOperator operator) {
        this.operator = operator;
    }
}