package com.github.ayushpatodi.banktransactionvalidationservice.processor;

import com.github.ayushpatodi.banktransactionvalidationservice.constants.Constants;
import com.github.ayushpatodi.banktransactionvalidationservice.dto.TransactionRecordDTO;
import com.github.ayushpatodi.banktransactionvalidationservice.exception.BankTransactionValidationException;
import com.github.ayushpatodi.banktransactionvalidationservice.service.FileProcessingService;
import com.github.ayushpatodi.banktransactionvalidationservice.service.ValidationService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * This class implements TransactionFileProcess interface
 *
 * @author Ayush Patodi
 */
@Component
@Log4j2
public class BankTransactionFileProcessorImpl implements BankTransactionFileProcessor {

    private final ValidationService validationService;
    @Qualifier(Constants.JSON_SERVICE_IMPL_QUALIFIER)
    private final FileProcessingService jsonFileProcessingService;
    @Qualifier(Constants.CSV_SERVICE_IMPL_QUALIFIER)
    private final FileProcessingService csvFileProcessingService;

    /**
     * Instantiates a new Bank transaction file processor.
     *
     * @param validationService         the validation service
     * @param jsonFileProcessingService the json file processing service
     * @param csvFileProcessingService  the csv file processing service
     */
    @Autowired
    public BankTransactionFileProcessorImpl(ValidationService validationService,
                                            FileProcessingService jsonFileProcessingService,
                                            FileProcessingService csvFileProcessingService) {
        this.validationService = validationService;
        this.jsonFileProcessingService = jsonFileProcessingService;
        this.csvFileProcessingService = csvFileProcessingService;
    }


    @Override
    public void processTransactionRecordFile(final String inputFilePath, final String outputFilePath)
            throws BankTransactionValidationException {

        List<TransactionRecordDTO> transactionRecordDTOS = parseTransactionRecordFile(inputFilePath);

        List<TransactionRecordDTO> invalidRecords = validationService.validateTransactionRecords(transactionRecordDTOS);

        generateInvalidRecordsReport(outputFilePath, invalidRecords);
    }

    private List<TransactionRecordDTO> parseTransactionRecordFile(final String inputFilePath) {

        final String inputFileType = FilenameUtils.getExtension(inputFilePath);

        if (!inputFileType.isEmpty()) {
            if (Constants.JSON.equalsIgnoreCase(inputFileType)) {
                return jsonFileProcessingService.parseFile(inputFilePath);
            } else if (Constants.CSV.equalsIgnoreCase(inputFileType)) {
                return csvFileProcessingService.parseFile(inputFilePath);
            } else {
                log.error(Constants.ERROR_LOG_INPUT_FILE_INVALID_FILE_TYPE);
                throw new BankTransactionValidationException(Constants.EXCEPTION_LOG_INPUT_FILE_INVALID_FILE_TYPE);
            }
        } else {
            log.error(Constants.ERROR_LOG_INPUT_FILE_FILE_TYPE_NOT_DETERMINED);
            throw new BankTransactionValidationException(Constants.EXCEPTION_LOG_INPUT_FILE_FILE_TYPE_NOT_DETERMINED);
        }
    }


    private void generateInvalidRecordsReport(final String outputFilePath, List<TransactionRecordDTO> invalidRecords) {

        final String outputFileType = FilenameUtils.getExtension(outputFilePath);

        boolean reportCreated;
        if (!outputFilePath.isEmpty()) {
            if (Constants.JSON.equalsIgnoreCase(outputFileType)) {
                reportCreated = jsonFileProcessingService.createReport(invalidRecords, outputFilePath);
            } else if (Constants.CSV.equalsIgnoreCase(outputFileType)) {
                reportCreated = csvFileProcessingService.createReport(invalidRecords, outputFilePath);
            } else {
                log.error(Constants.ERROR_LOG_OUTPUT_FILE_INVALID_FILE_TYPE);
                throw new BankTransactionValidationException(Constants.EXCEPTION_LOG_OUTPUT_FILE_INVALID_FILE_TYPE);
            }
            if (reportCreated) {
                log.info(Constants.REPORT_GENERATED_SUCCESSFULLY);
            }
        } else {
            log.error(Constants.ERROR_LOG_OUTPUT_FILE_FILE_TYPE_NOT_DETERMINED);
            throw new BankTransactionValidationException(Constants.EXCEPTION_LOG_OUTPUT_FILE_FILE_TYPE_NOT_DETERMINED);
        }
    }

}
