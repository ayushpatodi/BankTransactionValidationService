package com.github.ayushpatodi.banktransactionvalidationservice.service;


import com.github.ayushpatodi.banktransactionvalidationservice.constants.Constants;
import com.github.ayushpatodi.banktransactionvalidationservice.dto.TransactionRecordDTO;
import com.github.ayushpatodi.banktransactionvalidationservice.exception.BankTransactionValidationException;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

/**
 * This class implements File utility interface for CSV files
 *
 * @author Ayush Patodi
 */
@Component(Constants.CSV_SERVICE_IMPL_QUALIFIER)
@Log4j2
public class CsvFileProcessingServiceImpl implements FileProcessingService {

    @Override
    public List<TransactionRecordDTO> parseFile(String filePath) throws BankTransactionValidationException {
        try {
            Reader reader = new BufferedReader(new FileReader(new File(filePath)));

            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(TransactionRecordDTO.class)
                    .withSeparator(',')
                    .withIgnoreEmptyLine(true)
                    .build();

            return csvToBean.parse();

        } catch (NumberFormatException numberFormatException) {
            log.error(Constants.ERROR_LOG_CSV_FILE_NUMBER_FORMAT_ERROR, numberFormatException);
            throw new BankTransactionValidationException
                    (Constants.EXCEPTION_LOG_CSV_FILE_NUMBER_FORMAT_ERROR + numberFormatException.getMessage());
        } catch (FileNotFoundException fileNotFoundException) {
            log.error(Constants.ERROR_LOG_CSV_FILE_NOT_FOUND, fileNotFoundException);
            throw new BankTransactionValidationException
                    (Constants.EXCEPTION_LOG_CSV_FILE_NOT_FOUND + fileNotFoundException.getMessage());
        } catch (IllegalStateException illegalStateException) {
            log.error(Constants.EXCEPTION_LOG_CSV_FILE_READ, illegalStateException);
            throw new BankTransactionValidationException(
                    (Constants.EXCEPTION_LOG_CSV_FILE_READ + illegalStateException.getMessage()));
        }
    }

    @Override
    public boolean createReport(List<TransactionRecordDTO> transactionRecordDTOS, String filePath)
            throws BankTransactionValidationException {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(filePath)));
            String[] header = {Constants.CSV_FILE_HEADER};
            csvWriter.writeNext(header);
            for (TransactionRecordDTO transactionRecordDTO : transactionRecordDTOS) {
                String[] data = {String.valueOf(transactionRecordDTO.getReference()), transactionRecordDTO.getDescription()};
                csvWriter.writeNext(data);
            }
            csvWriter.close();
            return true;
        } catch (IOException e) {
            log.error(Constants.EXCEPTION_LOG_CSV_FILE_WRITE, e);
            throw new BankTransactionValidationException(
                    (Constants.EXCEPTION_LOG_CSV_FILE_WRITE + e.getMessage()));
        }
    }

}
