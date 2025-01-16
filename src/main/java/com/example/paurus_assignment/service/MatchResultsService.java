package com.example.paurus_assignment.service;

import com.example.paurus_assignment.model.MatchResultRequest;
import com.example.paurus_assignment.repository.MatchResultsRepository;
import org.postgresql.copy.CopyManager;
import org.springframework.stereotype.Service;

import org.postgresql.PGConnection;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.List;
import java.util.stream.Stream;

@Service
public class MatchResultsService {

    private  final MatchResultsRepository matchResultsRepository;

    public MatchResultsService(MatchResultsRepository matchResultsRepository) {
        this.matchResultsRepository = matchResultsRepository;
    }

    public void insertMatchResultRequests(List<MatchResultRequest> matchResultRequests) {
        final Stream<MatchResultRequest> matchResults = matchResultRequests.stream().sorted();

        try {
           final StringBuffer buffer = new StringBuffer();

            matchResults.forEach(matchResultRequest ->
                    buffer.append(matchResultRequest.toString()).append("\n")
            );

            final String copyCommand = "COPY match_result (match_id, market_id, outcome_id, specifiers) FROM STDIN WITH (FORMAT csv, NULL 'null')";


            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/assignment_db", "admin","password")) {
              final PGConnection pgConnection = connection.unwrap(PGConnection.class);
              final CopyManager copyManager = pgConnection.getCopyAPI();

                try (ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer.toString().getBytes())) {
                    copyManager.copyIn(copyCommand, inputStream);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }

        Timestamp firstTimestamp =  matchResultsRepository.findFirstByOrderByDateInsertAsc().get().getDate_insert();
        Timestamp lastTimestamp = matchResultsRepository.findLastByOrderByDateInsertDesc().get().getDate_insert();
        System.out.println("Insertion time is " + (lastTimestamp.getTime() - firstTimestamp.getTime()) / 1000.0 + " seconds");
        System.out.println("First timestamp is " + firstTimestamp);
        System.out.println("Last timestamp is " + lastTimestamp);
    }
}
