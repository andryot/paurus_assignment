package com.example.paurus_assignment.controller;

import com.example.paurus_assignment.model.TaxResponse;
import com.example.paurus_assignment.model.TaxationRequest;
import com.example.paurus_assignment.service.TaxService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/taxation")
public class TaxationController {
    private final TaxService taxationService;

    public TaxationController(TaxService taxationService) {
        this.taxationService = taxationService;
    }

    @PostMapping("/calculate-tax")
    @ResponseStatus(HttpStatus.OK)
    public TaxResponse calculateTax(@Valid @RequestBody TaxationRequest request) {
        return taxationService.calculateTax(request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
