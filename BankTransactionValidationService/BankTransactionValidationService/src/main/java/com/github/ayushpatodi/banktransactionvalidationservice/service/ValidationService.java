package com.github.ayushpatodi.banktransactionvalidationservice.service;


import com.github.ayushpatodi.banktransactionvalidationservice.dto.TransactionRecordDTO;

import java.util.List;

/**
 * This interface handles methods for all File level processing
 *
 * @author Ayush Patodi
 */

public interface ValidationService {

    /**
     * Validate transaction records list.
     *
     * @param transactionRecordDTOS  - provides list of records that needs to be validatedthe transaction record dtos
     * @return the list TransactionRecordDTO - returns list of complex object type TransactionRecordDTO
     */
    List<TransactionRecordDTO> validateTransactionRecords(List<TransactionRecordDTO> transactionRecordDTOS);

}
