package com.example.paurus_assignment.service;

import com.example.paurus_assignment.model.TaxResponse;
import com.example.paurus_assignment.model.TaxationRequest;
import org.springframework.stereotype.Service;

@Service
public class TaxService {
    private static final double taxRate = 10;
    private static final double generalTaxAmount = 2;
    private static final double winningsTaxAmount = 1;

    public TaxResponse calculateTax(TaxationRequest taxationRequest) {
        final TaxationType taxationType = determineTaxationType(taxationRequest.traderId());

        return switch (taxationType) {
            case general -> calculateTaxGeneral(taxationRequest);
            case winnings -> calculateTaxWinnings(taxationRequest);
        };
    }

    private TaxResponse calculateTaxGeneral(TaxationRequest request) {
        final double odd = request.odd();
        final double playedAmount = request.playedAmount();

        final double possibleReturnAmountBeforeTax = playedAmount * odd;
        final double taxAmount = possibleReturnAmountBeforeTax * (taxRate / 100);

        final double possibleReturnAmount = possibleReturnAmountBeforeTax - generalTaxAmount;

        final double possibleReturnAmountAfterTax = possibleReturnAmountBeforeTax - taxAmount;

        return new TaxResponse(
                possibleReturnAmount,
                possibleReturnAmountBeforeTax,
                possibleReturnAmountAfterTax,
                taxRate,
                taxAmount
        );
    }

    private TaxResponse calculateTaxWinnings(TaxationRequest request) {
        final double odd = request.odd();
        final double playedAmount = request.playedAmount();

        final double possibleReturnAmountBeforeTax = playedAmount * odd;

        final double winningsAmount = possibleReturnAmountBeforeTax - playedAmount;

        final double taxAmount = winningsAmount * (taxRate / 100);

        // this one feels weird in the instructions. I think it should be same as in the general taxation
        // -> possibleReturnAmount = possibleReturnAmountBeforeTax - winningsTaxAmount;
        final double possibleReturnAmount = winningsAmount - winningsTaxAmount;

        final double possibleReturnAmountAfterTax = possibleReturnAmountBeforeTax - taxAmount;

        return new TaxResponse(
                possibleReturnAmount,
                possibleReturnAmountBeforeTax,
                possibleReturnAmountAfterTax,
                taxRate,
                taxAmount
        );
    }

    public enum TaxationType {general, winnings;}

    // mocked determination of Taxation type based on traderId
    private TaxationType determineTaxationType(int traderId) {
        return (traderId % 2 == 1) ? TaxationType.general : TaxationType.winnings;
    }
}