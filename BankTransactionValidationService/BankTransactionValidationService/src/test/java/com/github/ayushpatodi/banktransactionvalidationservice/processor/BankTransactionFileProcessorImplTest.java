package com.github.ayushpatodi.banktransactionvalidationservice.processor;

import com.github.ayushpatodi.banktransactionvalidationservice.dto.TransactionRecordDTO;
import com.github.ayushpatodi.banktransactionvalidationservice.exception.BankTransactionValidationException;
import com.github.ayushpatodi.banktransactionvalidationservice.service.FileProcessingService;
import com.github.ayushpatodi.banktransactionvalidationservice.service.ValidationService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BankTransactionFileProcessorImplTest {
    private static final String JSON_INPUT_FILE = "BankTransactionValidationService\\BankTransactionValidationService\\src\\test\\recordstestfile.json";
    private static final String JSON_OUTPUT_FILE = "BankTransactionValidationService\\BankTransactionValidationService\\src\\test\\testreport.json";
    private static final String CSV_INPUT_FILE = "BankTransactionValidationService\\BankTransactionValidationService\\src\\test\\recordstestfile.csv";
    private static final String CSV_OUTPUT_FILE = "BankTransactionValidationService\\BankTransactionValidationService\\src\\test\\testreport.csv";


    @Mock
    private ValidationService validationService;
    @Mock
    private FileProcessingService jsonFileProcessingServiceImpl;
    @Mock
    private FileProcessingService csvFileProcessingServiceImpl;

    private BankTransactionFileProcessorImpl transactionFileProcessor;

    @BeforeEach
    void setUp() {
        transactionFileProcessor = new BankTransactionFileProcessorImpl(validationService, jsonFileProcessingServiceImpl, csvFileProcessingServiceImpl);
    }

    @SneakyThrows
    @Test
    void testRunForJsonFile_HappyFlow() {
        //given
        List<TransactionRecordDTO> transactionRecordDTOList = new ArrayList<>();
        TransactionRecordDTO transactionRecordDTO = new TransactionRecordDTO(
                12345,
                "NL29RABO0123456789",
                "Description 01",
                100.00,
                12,
                112.00);
        transactionRecordDTOList.add(transactionRecordDTO);

        Mockito.when(jsonFileProcessingServiceImpl.parseFile(JSON_INPUT_FILE))
                .thenReturn(transactionRecordDTOList);
        Mockito.when(validationService.validateTransactionRecords(transactionRecordDTOList))
                .thenReturn(transactionRecordDTOList);
        Mockito.when(jsonFileProcessingServiceImpl.createReport(transactionRecordDTOList, JSON_OUTPUT_FILE))
                .thenReturn(true);
        //when & then
        assertDoesNotThrow(() -> transactionFileProcessor.processTransactionRecordFile(JSON_INPUT_FILE, JSON_OUTPUT_FILE));
    }

    @SneakyThrows
    @Test
    void testRunForCSVFile_HappyFlow() {
        //given
        List<TransactionRecordDTO> transactionRecordDTOList = new ArrayList<>();
        TransactionRecordDTO transactionRecordDTO = new TransactionRecordDTO(
                12345,
                "NL29RABO0123456789",
                "Description 01",
                100.00,
                12,
                112.00);
        transactionRecordDTOList.add(transactionRecordDTO);

        Mockito.when(csvFileProcessingServiceImpl.parseFile(CSV_INPUT_FILE))
                .thenReturn(transactionRecordDTOList);
        Mockito.when(validationService.validateTransactionRecords(transactionRecordDTOList))
                .thenReturn(transactionRecordDTOList);
        Mockito.when(csvFileProcessingServiceImpl.createReport(transactionRecordDTOList, CSV_OUTPUT_FILE))
                .thenReturn(true);
        //when & then
        assertDoesNotThrow(() -> transactionFileProcessor.processTransactionRecordFile(CSV_INPUT_FILE, CSV_OUTPUT_FILE));
    }

    @SneakyThrows
    @Test
    void testRunForNonCsvJsonFile_HappyFlow() {
        assertThrows(BankTransactionValidationException.class,
                () -> transactionFileProcessor.processTransactionRecordFile("file.txt", "outputfile.txt"));
    }
}

