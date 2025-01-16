package com.example.paurus_assignment.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TaxationRequest(@NotNull(message = "Trader ID cannot be null") Integer traderId,
                              @Positive(message = "Played amount must be positive") double playedAmount,
                              @Min(value = 1, message = "Odds must be at least 1") double odd) {
}


