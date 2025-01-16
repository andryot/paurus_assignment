package com.example.paurus_assignment.repository;

import com.example.paurus_assignment.entity.MatchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MatchResultsRepository extends JpaRepository<MatchResult, Integer> {
    @Query("SELECT m FROM MatchResult m ORDER BY m.date_insert ASC LIMIT 1")
    Optional<MatchResult> findFirstByOrderByDateInsertAsc();

    @Query("SELECT m FROM MatchResult m ORDER BY m.date_insert DESC LIMIT 1")
    Optional<MatchResult> findLastByOrderByDateInsertDesc();
}
