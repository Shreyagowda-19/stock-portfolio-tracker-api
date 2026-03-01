package com.shreya.stockportfolio.dto;

public class PortfolioSummary {

    private double totalInvestment;
    private double currentValue;
    private double totalProfit;

    public PortfolioSummary(double totalInvestment, double currentValue, double totalProfit) {
        this.totalInvestment = totalInvestment;
        this.currentValue = currentValue;
        this.totalProfit = totalProfit;
    }

    public double getTotalInvestment() {
        return totalInvestment;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public double getTotalProfit() {
        return totalProfit;
    }
}