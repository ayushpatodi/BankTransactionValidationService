package com.github.ayushpatodi.banktransactionvalidationservice.service;


import com.github.ayushpatodi.banktransactionvalidationservice.constants.Constants;
import com.github.ayushpatodi.banktransactionvalidationservice.dto.TransactionRecordDTO;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class implements ValidationSerivce interface
 *
 * @author Ayush Patodi
 */

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public List<TransactionRecordDTO> validateTransactionRecords(List<TransactionRecordDTO> transactionRecordDTOS) {
        Set<Long> uniqueRecords = new HashSet<>();

        Set<Long> duplicateRecordsSet = transactionRecordDTOS.stream()
                .filter(transactionRecordDTO -> !uniqueRecords.add(transactionRecordDTO.getReference()))
                .map(TransactionRecordDTO::getReference).collect(Collectors.toCollection(LinkedHashSet::new));

        DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT_PATTERN);
        return transactionRecordDTOS.stream()
                .filter(transactionRecordDTO -> (duplicateRecordsSet.contains(transactionRecordDTO.getReference())
                        || transactionRecordDTO.getEndBalance() != Double.parseDouble
                        (decimalFormat.format(transactionRecordDTO.getMutation() + transactionRecordDTO.getStartBalance()))))
                .collect(Collectors.toList());
    }
}