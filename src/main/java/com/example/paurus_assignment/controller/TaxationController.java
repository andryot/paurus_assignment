package com.example.paurus_assignment.controller;

import com.example.paurus_assignment.model.TaxResponse;
import com.example.paurus_assignment.model.TaxationRequest;
import com.example.paurus_assignment.service.TaxService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/taxation")
public class TaxationController {
    private final TaxService taxationService;

    public TaxationController(TaxService taxationService) {
        this.taxationService = taxationService;
    }

    @PostMapping("/calculate-tax")
    public TaxResponse calculateTax(@RequestBody TaxationRequest request) {
        return taxationService.(request);
    }
}
