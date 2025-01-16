package com.example.paurus_assignment.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class MatchResult {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String match_id;
    private Integer market_id;
    private String outcome_id;
    @Nullable
    private String specifiers;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp date_insert;


    public MatchResult() {}

    public MatchResult(String match_id, Integer market_id, String outcome_id, @Nullable  String specifiers) {
        this.match_id = match_id;
        this.market_id = market_id;
        this.outcome_id = outcome_id;
        this.specifiers = specifiers;
    }

    public Long getId() {
        return id;
    }

    public String getMatch_id() {
        return match_id;
    }

    public Integer getMarket_id() {
        return market_id;
    }

    public String getOutcome_id() {
        return outcome_id;
    }

    public String getSpecifiers() {
        return specifiers;
    }

    public Timestamp getDate_insert() {
        return date_insert;
    }
}

