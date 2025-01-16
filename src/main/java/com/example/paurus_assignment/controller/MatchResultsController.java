package com.example.paurus_assignment.controller;

import com.example.paurus_assignment.model.MatchResultRequest;
import com.example.paurus_assignment.service.MatchResultsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match-results")
public class MatchResultsController {

    private final MatchResultsService matchResultsService;

    public MatchResultsController(MatchResultsService matchResultsService) {
        this.matchResultsService = matchResultsService;
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody List<MatchResultRequest> matchResultRequests) {
        matchResultsService.insertMatchResultRequests(matchResultRequests);
    }

}
