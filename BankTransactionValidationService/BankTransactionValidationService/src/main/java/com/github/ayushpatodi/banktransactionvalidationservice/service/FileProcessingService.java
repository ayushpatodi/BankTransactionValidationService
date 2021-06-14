package com.github.ayushpatodi.banktransactionvalidationservice.service;

import com.github.ayushpatodi.banktransactionvalidationservice.dto.TransactionRecordDTO;
import com.github.ayushpatodi.banktransactionvalidationservice.exception.BankTransactionValidationException;

import java.util.List;

/**
 * This interface handles methods for all File level processing
 *
 * *@author Ayush Patodi
 */
public interface FileProcessingService {

    /**
     * Parse input file.
     *
     * @param filePath - provides the path of the files that needs to be parsed
     * @return List TransactionRecordDTO - returns the list of Transaction Records parsed from the file
     * @throws BankTransactionValidationException the bank transaction validation exception
     */
    List<TransactionRecordDTO> parseFile(String filePath) throws BankTransactionValidationException;


    /**
     * method to create the report of input records
     *
     * @param transactionRecordDTOS the transaction record dtos
     * @param filePath - provides the path of the files in which records need to be written
     * @return the boolean
     * @throws BankTransactionValidationException the bank transaction validation exception
     */
    boolean createReport(List<TransactionRecordDTO> transactionRecordDTOS, String filePath) throws BankTransactionValidationException;
}
