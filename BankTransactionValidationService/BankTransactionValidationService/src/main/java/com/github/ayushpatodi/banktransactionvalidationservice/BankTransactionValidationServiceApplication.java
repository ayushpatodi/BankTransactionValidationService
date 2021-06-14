package com.github.ayushpatodi.banktransactionvalidationservice;

import com.github.ayushpatodi.banktransactionvalidationservice.exception.BankTransactionValidationException;
import com.github.ayushpatodi.banktransactionvalidationservice.processor.BankTransactionFileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Bank transaction validation service application.
 */
@SpringBootApplication
public class BankTransactionValidationServiceApplication implements CommandLineRunner {


    private final BankTransactionFileProcessor bankTransactionFileProcessor;

    /**
     * Instantiates a new Bank transaction validation service application.
     *
     * @param bankTransactionFileProcessor the bank transaction file processor
     */
    @Autowired
    public BankTransactionValidationServiceApplication(BankTransactionFileProcessor bankTransactionFileProcessor) {
        this.bankTransactionFileProcessor = bankTransactionFileProcessor;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BankTransactionValidationServiceApplication.class, args);
    }

    @Override
    public void run(String[] args) throws BankTransactionValidationException {

        String inputTransactionRecordFilePath = "";
        String outputReportFilePath = "";

        if (2 == args.length) {
            inputTransactionRecordFilePath = args[0];
            outputReportFilePath = args[1];
        }

        bankTransactionFileProcessor.processTransactionRecordFile(inputTransactionRecordFilePath, outputReportFilePath);
    }
}
