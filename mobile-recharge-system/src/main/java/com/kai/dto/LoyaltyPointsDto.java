package com.kai.dto;

public class LoyaltyPointsDto {

    private Long userId;
    private Integer points;


    public LoyaltyPointsDto(Long userId, Integer points) {
        this.userId = userId;
        this.points = points;
    }


    public Long getUserId() {
        return userId;
    }


    public Integer getPoints() {
        return points;
    }

}