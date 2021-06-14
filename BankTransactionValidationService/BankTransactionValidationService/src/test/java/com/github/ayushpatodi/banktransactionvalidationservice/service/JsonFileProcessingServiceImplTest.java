package com.github.ayushpatodi.banktransactionvalidationservice.service;

import com.github.ayushpatodi.banktransactionvalidationservice.dto.TransactionRecordDTO;
import com.github.ayushpatodi.banktransactionvalidationservice.exception.BankTransactionValidationException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonFileProcessingServiceImplTest {
    private static final String JSON_INPUT_FILE = "src\\test\\recordstestfile.json";
    private static final String JSON_OUTPUT_FILE = "src\\test\\testreport.json";

    private JsonFileProcessingServiceImpl jsonFileProcessingService;

    @BeforeEach
    void setUp() {
        jsonFileProcessingService = new JsonFileProcessingServiceImpl();
    }

    @AfterEach
    void tearDown() {
        File report = new File(JSON_OUTPUT_FILE);
        if (report.exists()) {
            report.delete();
        }
    }

    @SneakyThrows
    @Test
    void parseValidJSONFile_HappyFlow() {
        String filePath = JSON_INPUT_FILE;

        List<TransactionRecordDTO> recordDTOList = jsonFileProcessingService.parseFile(filePath);

        assertAll(
                () -> assertEquals(11, recordDTOList.size())
        );
    }

    @SneakyThrows
    @Test
    void parseNonExistingFile_NonHappyFlow() {
        String filePath = "file";
        assertThrows(BankTransactionValidationException.class,
                () -> jsonFileProcessingService.parseFile(filePath));
    }

    @SneakyThrows
    @Test
    void generateReportFile_HappyFlow() {
        List<TransactionRecordDTO> transactionRecordDTOS = createInvalidTransactionRecordDTO();

        String filePath = JSON_OUTPUT_FILE;

        boolean fileCreated = jsonFileProcessingService.createReport(transactionRecordDTOS, filePath);

        assertTrue(fileCreated);

    }


    List<TransactionRecordDTO> createInvalidTransactionRecordDTO() {
        List<TransactionRecordDTO> transactionRecordDTOList = new ArrayList<>();
        TransactionRecordDTO dto1 = new TransactionRecordDTO(
                165102,
                "NL93ABNA0585619023",
                "Book Shevaun Taylor",
                3980.0, 1000.0, 4981.0
        );
        transactionRecordDTOList.add(dto1);
        TransactionRecordDTO dto2 = new TransactionRecordDTO(
                167875,
                "NL93ABNA0585619023",
                "Toy Greg Alysha",
                5429.0, -939.0, 6368.0
        );
        transactionRecordDTOList.add(dto2);
        TransactionRecordDTO dto3 = new TransactionRecordDTO(
                112806,
                "NL27SNSB0917829871",
                "Clothes Irma Steven",
                91.23, +15.57, 106.8
        );
        transactionRecordDTOList.add(dto3);
        TransactionRecordDTO dto4 = new TransactionRecordDTO(
                112806,
                "NL69ABNA0433647324",
                "Book Peter de Vries",
                90.83, -10.91, 79.92
        );
        transactionRecordDTOList.add(dto4);
        TransactionRecordDTO dto5 = new TransactionRecordDTO(
                112806,
                "NL93ABNA0585619023",
                "Book Richard Tyson",
                102.12, +45.87, 147.99
        );
        transactionRecordDTOList.add(dto5);
        return transactionRecordDTOList;
    }
}
