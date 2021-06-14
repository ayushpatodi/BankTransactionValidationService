package com.github.ayushpatodi.banktransactionvalidationservice;

import com.github.ayushpatodi.banktransactionvalidationservice.processor.BankTransactionFileProcessor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(args = {"src\\test\\recordstestfile.json", "src\\test\\testreport.json"})
@ExtendWith(MockitoExtension.class)
public class BankTransactionValidationServiceApplicationTests {

    private static final String JSON_INPUT_FILE = "src\\test\\recordstestfile.json";
    private static final String JSON_OUTPUT_FILE = "src\\test\\testreport.json";
    private static final String CSV_OUTPUT_FILE = "src\\test\\testreport.csv";

    private BankTransactionValidationServiceApplication bankTransactionValidationServiceApplication;
    @Mock
    BankTransactionFileProcessor bankTransactionFileProcessor;

    @BeforeEach
    void setUp() {
        bankTransactionValidationServiceApplication = new BankTransactionValidationServiceApplication(bankTransactionFileProcessor);
    }

    @AfterEach
    void tearDown() {
        File report = new File(JSON_OUTPUT_FILE);
        if (report.exists()) {
            report.delete();
        }
        File reportCsv = new File(CSV_OUTPUT_FILE);
        if (reportCsv.exists()) {
            reportCsv.delete();
        }
    }

    @Test
    void contextLoads() {
    }

    @SneakyThrows
    @Test
    void testRunForJsonFile_HappyFlow() {

        String[] args = {JSON_INPUT_FILE, JSON_OUTPUT_FILE};
        File report = new File(JSON_OUTPUT_FILE);

        bankTransactionValidationServiceApplication.run(args);

        assertTrue(report.exists());

    }
}
