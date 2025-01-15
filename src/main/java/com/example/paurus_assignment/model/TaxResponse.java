package com.example.paurus_assignment.model;


public class TaxResponse {
    private  final double possibleReturnAmount;
    private final double possibleReturnAmountBefTax;
    private final double possibleReturnAmountAfterTax;
    private final double taxRate;
    private final double taxAmount;


    public TaxResponse(double possibleReturnAmount, double possibleReturnAmountBefTax, double possibleReturnAmountAfterTax, double taxRate, double taxAmount) {
        this.possibleReturnAmount = possibleReturnAmount;
        this.possibleReturnAmountBefTax = possibleReturnAmountBefTax;
        this.possibleReturnAmountAfterTax = possibleReturnAmountAfterTax;
        this.taxRate = taxRate;
        this.taxAmount = taxAmount;
    }

    public double getPossibleReturnAmount() {
        return possibleReturnAmount;
    }

    public double getPossibleReturnAmountBefTax() {
        return possibleReturnAmountBefTax;
    }

    public double getPossibleReturnAmountAfterTax() {
        return possibleReturnAmountAfterTax;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getTaxAmount() {
        return taxAmount;
    }
}
