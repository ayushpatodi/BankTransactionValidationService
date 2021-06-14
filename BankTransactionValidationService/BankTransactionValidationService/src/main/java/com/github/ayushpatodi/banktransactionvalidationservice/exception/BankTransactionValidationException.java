package com.github.ayushpatodi.banktransactionvalidationservice.exception;

/*  *Custom exception class for defining exception handling for Bank Transaction Validation Service
 *
 *@author Ayush Patodi*/
public class BankTransactionValidationException extends RuntimeException {
    private String message;

    /*   *parameterized constructor
     *
     *@param message exception message*/
    public BankTransactionValidationException(String message) {
        super(message);
    }
}
