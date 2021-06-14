package com.github.ayushpatodi.banktransactionvalidationservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ayushpatodi.banktransactionvalidationservice.constants.Constants;
import com.github.ayushpatodi.banktransactionvalidationservice.dto.TransactionRecordDTO;
import com.github.ayushpatodi.banktransactionvalidationservice.exception.BankTransactionValidationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * This class implements FileProcessingService interface for Json files
 *
 * @author Ayush Patodi
 */
@Component(Constants.JSON_SERVICE_IMPL_QUALIFIER)
@Log4j2
public class JsonFileProcessingServiceImpl implements FileProcessingService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<TransactionRecordDTO> parseFile(String filePath) throws BankTransactionValidationException {
        try {

            return mapper.readValue(new File(filePath), new TypeReference<List<TransactionRecordDTO>>() {
            });

        } catch (JsonMappingException jsonMappingException) {
            log.error(Constants.ERROR_LOG_JSON_FILE_NUMBER_FORMAT_ERROR, jsonMappingException);
            throw new BankTransactionValidationException(
                    (Constants.EXCEPTION_LOG_JSON_FILE_NUMBER_FORMAT_ERROR + jsonMappingException.getMessage()));
        } catch (FileNotFoundException fileNotFoundException) {
            log.error(Constants.ERROR_LOG_JSON_FILE_NOT_FOUND, fileNotFoundException);
            throw new BankTransactionValidationException(
                    (Constants.EXCEPTION_LOG_JSON_FILE_NOT_FOUND + fileNotFoundException.getMessage()));
        } catch (IOException ioException) {
            log.error(Constants.EXCEPTION_LOG_JSON_FILE_READ, ioException);
            throw new BankTransactionValidationException(
                    (Constants.EXCEPTION_LOG_JSON_FILE_READ + ioException.getMessage()));
        }
    }

    @Override
    public boolean createReport(List<TransactionRecordDTO> transactionRecordDTOS, String filePath) throws BankTransactionValidationException {
        try {
            mapper.writeValue(new File(filePath), transactionRecordDTOS);
            return true;

        } catch (IOException ioException) {
            log.error(Constants.EXCEPTION_LOG_JSON_FILE_WRITE, ioException);
            throw new BankTransactionValidationException(
                    (Constants.EXCEPTION_LOG_JSON_FILE_WRITE + ioException.getMessage()));
        }
    }
}
