package com.example.paurus_assignment.service;


import com.example.paurus_assignment.model.TaxResponse;
import com.example.paurus_assignment.model.TaxationRequest;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private static final double taxRate = 10;
    private static  final double generalTaxAmount = 2;
    private static  final double winningsTaxAmount = 1;


    public TaxResponse calculateTax(TaxationRequest taxationRequest) {
        final TaxationType taxationType;

        taxationType = switch (taxationRequest.traderId()){
            case 2 -> TaxationType.winnings;
            default -> TaxationType.general;
        };

        return switch (taxationType) {
            case general -> calculateTaxGeneral(taxationRequest);
            case winnings -> calculateTaxWinnings(taxationRequest);
        };
    }

    private TaxResponse calculateTaxGeneral(TaxationRequest request) {
        double odd = request.odd();
        double playedAmount = request.playedAmount();

        double totalWinning = playedAmount * odd;

        double taxAmount = totalWinning * (taxRate / 100);

        double possibleReturnAmount = totalWinning - generalTaxAmount;

        double possibleReturnAmountBeforeTax = totalWinning - playedAmount;
        double possibleReturnAmountAfterTax = totalWinning - taxAmount;

        return new TaxResponse(
                possibleReturnAmount,
                possibleReturnAmountBeforeTax,
                possibleReturnAmountAfterTax,
                taxRate,
                taxAmount
        );
    }
    private TaxResponse calculateTaxWinnings(TaxationRequest request) {
        double odd = request.odd();
        double playedAmount = request.playedAmount();

        double totalWinning = playedAmount * odd;

        double winningsAmount = totalWinning - playedAmount;

        double taxAmount = winningsAmount * (winningsTaxAmount / 100);

        double possibleReturnAmount = totalWinning - winningsTaxAmount;

        double possibleReturnAmountBeforeTax = totalWinning - playedAmount;
        double possibleReturnAmountAfterTax = possibleReturnAmount - winningsTaxAmount;

        return new TaxResponse(
                possibleReturnAmount,
                possibleReturnAmountBeforeTax,
                possibleReturnAmountAfterTax,
                taxRate,
                taxAmount
        );
    }

    public enum TaxationType {general, winnings,}
}