package com.github.ayushpatodi.banktransactionvalidationservice.processor;

import com.github.ayushpatodi.banktransactionvalidationservice.exception.BankTransactionValidationException;


/**
 * This is an interface to validate and process the input file
 */
public interface BankTransactionFileProcessor {

    /**
     * method to process input file and create output file
     *
     * @param inputFilePath  the input file path
     * @param outputFilePath the output file path where invalid records should be written
     * @throws BankTransactionValidationException the bank transaction validation exception
     */
    void processTransactionRecordFile(final String inputFilePath, final String outputFilePath) throws BankTransactionValidationException;

}
