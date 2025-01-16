package com.example.paurus_assignment.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.Comparator;

public record MatchResultRequest(@NotNull(message = "MATCH_ID cannot be null") String MATCH_ID,
                                 @NotNull(message = "MATCH_ID cannot be null") Integer MARKET_ID,
                                 @NotNull(message = "RESULT_ID cannot be null") String RESULT_ID,
                                 @Nullable String  SPECIFIERS) implements Comparable {

    @Override
    public String MATCH_ID() {
        return MATCH_ID;
    }

    @Override
    public Integer MARKET_ID() {
        return MARKET_ID;
    }

    @Override
    public String RESULT_ID() {
        return RESULT_ID;
    }

    @Override
    @Nullable
    public String SPECIFIERS() {
        return SPECIFIERS;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof MatchResultRequest other)) {
            return 1;
        }
        return Comparator.comparing(MatchResultRequest::MATCH_ID)
                    .thenComparing(MatchResultRequest::MARKET_ID)
                    .thenComparing(MatchResultRequest::RESULT_ID)
                    .thenComparing(MatchResultRequest::SPECIFIERS, Comparator.nullsFirst(String::compareTo))
                    .compare(this, other);
    }

    @Override
    public String toString() {
        return MATCH_ID + "," + MARKET_ID + "," + RESULT_ID + "," + SPECIFIERS;
    }
}


