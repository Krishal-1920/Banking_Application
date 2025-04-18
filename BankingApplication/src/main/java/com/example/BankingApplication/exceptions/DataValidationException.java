package com.example.BankingApplication.exceptions;

import lombok.Data;

@Data
public class DataValidationException extends RuntimeException {
    public DataValidationException(String message) {
        super(message);
    }
}
