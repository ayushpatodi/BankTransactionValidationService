package com.github.ayushpatodi.banktransactionvalidationservice.service;

import com.github.ayushpatodi.banktransactionvalidationservice.dto.TransactionRecordDTO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ValidationServiceImplTest {
    private ValidationServiceImpl validationService;

    @BeforeEach
    void setUp() {
        validationService = new ValidationServiceImpl();
    }

    @SneakyThrows
    @Test
    void validateBankTransactionRecord_ForValidRecords_HappyFlow() {

        List<TransactionRecordDTO> transactionRecordDTOList = createValidTransactionRecordDTO();
        List<TransactionRecordDTO> expectedReportList = new ArrayList<>();

        List<TransactionRecordDTO> reportList = validationService.validateTransactionRecords(transactionRecordDTOList);

        assertAll(
                () -> assertEquals(expectedReportList.size(), reportList.size()),
                () -> assertArrayEquals(expectedReportList.toArray(), reportList.toArray())
        );
    }


    @SneakyThrows
    @Test
    void validateBankTransactionRecord_ForInvalidRecords_NonHappyFlow() {

        List<TransactionRecordDTO> transactionRecordDTOList = createInvalidTransactionRecordDTO();
        List<TransactionRecordDTO> expectedReportList = new ArrayList<>();

        TransactionRecordDTO dto1 = new TransactionRecordDTO(
                165102,
                "NL93ABNA0585619023",
                "Book Shevaun Taylor",
                3980.0, 1000.0, 4981.0
        );
        expectedReportList.add(dto1);
        TransactionRecordDTO dto2 = new TransactionRecordDTO(
                167875,
                "NL93ABNA0585619023",
                "Toy Greg Alysha",
                5429.0, -939.0, 6368.0
        );
        expectedReportList.add(dto2);
        TransactionRecordDTO dto3 = new TransactionRecordDTO(
                112806,
                "NL27SNSB0917829871",
                "Clothes Irma Steven",
                91.23, +15.57, 106.8
        );
        expectedReportList.add(dto3);
        TransactionRecordDTO dto4 = new TransactionRecordDTO(
                112806,
                "NL69ABNA0433647324",
                "Book Peter de Vries",
                90.83, -10.91, 79.92
        );
        expectedReportList.add(dto4);
        TransactionRecordDTO dto5 = new TransactionRecordDTO(
                112806,
                "NL93ABNA0585619023",
                "Book Richard Tyson",
                102.12, +45.87, 147.99
        );
        expectedReportList.add(dto5);

        List<TransactionRecordDTO> reportList = validationService.validateTransactionRecords(transactionRecordDTOList);

        assertAll(
                () -> assertEquals(expectedReportList.size(), reportList.size()),
                () -> assertNotNull(reportList)
        );
    }

    @SneakyThrows
    @Test
    void validateBankTransactionRecord_ForInvalidReferenceRecords_NonHappyFlow() {

        List<TransactionRecordDTO> transactionRecordDTOList = createInvalidReferenceTransactionRecordDTO();
        List<TransactionRecordDTO> expectedReportList = new ArrayList<>();


        TransactionRecordDTO dto1 = new TransactionRecordDTO(
                112806,
                "NL27SNSB0917829871",
                "Clothes Irma Steven",
                91.23, +15.57, 106.8
        );
        expectedReportList.add(dto1);
        TransactionRecordDTO dto3 = new TransactionRecordDTO(
                112806,
                "NL74ABNA0248990274",
                "Toy Jimmie Clarice",
                92.98, -46.65, 46.33
        );
        expectedReportList.add(dto3);
        TransactionRecordDTO dto4 = new TransactionRecordDTO(
                112806,
                "NL43AEGO0773393871",
                "Flowers Jan Tyson",
                99.44, +41.23, 140.67
        );
        expectedReportList.add(dto4);

        List<TransactionRecordDTO> reportList = validationService.validateTransactionRecords(transactionRecordDTOList);

        assertAll(
                () -> assertEquals(expectedReportList.size(), reportList.size()),
                () -> assertNotNull(reportList)
        );
    }

    @SneakyThrows
    @Test
    void validateBankTransactionRecord_ForInvalidEndBalanceRecords_NonHappyFlow() {

        List<TransactionRecordDTO> transactionRecordDTOList = createInvalidEndBalanceTransactionRecordDTO();
        List<TransactionRecordDTO> expectedReportList = new ArrayList<>();

        TransactionRecordDTO dto1 = new TransactionRecordDTO(
                183049,
                "NL69ABNA0433647324",
                "Book Arndt Thilo",
                86.66, +44.5, 131.00
        );
        expectedReportList.add(dto1);
        TransactionRecordDTO dto2 = new TransactionRecordDTO(
                183356,
                "NL74ABNA0248990274",
                "Toy Jimmie Clarice",
                92.98, -46.65, 139.63
        );
        expectedReportList.add(dto2);


        List<TransactionRecordDTO> reportList = validationService.validateTransactionRecords(transactionRecordDTOList);

        assertAll(
                () -> assertEquals(expectedReportList.size(), reportList.size()),
                () -> assertNotNull(reportList)
        );
    }

    // Initial TransactionRecordDTO data creation methods for different test flavours.

    List<TransactionRecordDTO> createValidTransactionRecordDTO() {
        List<TransactionRecordDTO> transactionRecordDTOList = new ArrayList<>();
        TransactionRecordDTO dto = new TransactionRecordDTO(
                194261,
                "NL91RABO0315273637",
                "Book John Smith",
                21.6, -41.83, -20.23
        );
        transactionRecordDTOList.add(dto);
        TransactionRecordDTO dto1 = new TransactionRecordDTO(
                112806,
                "NL27SNSB0917829871",
                "Clothes Irma Steven",
                91.23, +15.57, 106.8
        );
        transactionRecordDTOList.add(dto1);
        TransactionRecordDTO dto2 = new TransactionRecordDTO(
                183049,
                "NL69ABNA0433647324",
                "Book Arndt Thilo",
                86.66, +44.5, 131.16
        );
        transactionRecordDTOList.add(dto2);
        TransactionRecordDTO dto3 = new TransactionRecordDTO(
                183356,
                "NL74ABNA0248990274",
                "Toy Jimmie Clarice",
                92.98, -46.65, 46.33
        );
        transactionRecordDTOList.add(dto3);
        TransactionRecordDTO dto5 = new TransactionRecordDTO(
                139524,
                "NL43AEGO0773393871",
                "Flowers Jan Tyson",
                99.44, +41.23, 140.67
        );
        transactionRecordDTOList.add(dto5);
        TransactionRecordDTO dto6 = new TransactionRecordDTO(
                179430,
                "NL93ABNA0585619023",
                "Clothes Dolores Kerensa",
                23.96, -27.43, -3.47
        );
        transactionRecordDTOList.add(dto6);
        TransactionRecordDTO dto7 = new TransactionRecordDTO(
                141223,
                "NL93ABNA0585619023",
                "Book Mahala Kreszenz",
                94.25, +41.6, 135.85
        );
        transactionRecordDTOList.add(dto7);
        TransactionRecordDTO dto8 = new TransactionRecordDTO(
                195446,
                "NL74ABNA0248990274",
                "Toy Hal Shavonne",
                26.32, +48.98, 75.3
        );
        transactionRecordDTOList.add(dto8);
        return transactionRecordDTOList;
    }

    List<TransactionRecordDTO> createInvalidTransactionRecordDTO() {
        List<TransactionRecordDTO> TransactionRecordDTOList = new ArrayList<>();
        TransactionRecordDTO dto = new TransactionRecordDTO(
                194261,
                "NL91RABO0315273637",
                "Book John Smith",
                21.6, -41.83, -20.23
        );
        TransactionRecordDTOList.add(dto);
        TransactionRecordDTO dto1 = new TransactionRecordDTO(
                112806,
                "NL27SNSB0917829871",
                "Clothes Irma Steven",
                91.23, +15.57, 106.8
        );
        TransactionRecordDTOList.add(dto1);
        TransactionRecordDTO dto2 = new TransactionRecordDTO(
                165102,
                "NL93ABNA0585619023",
                "Book Shevaun Taylor",
                3980.0, 1000.0, 4981.0
        );
        TransactionRecordDTOList.add(dto2);
        TransactionRecordDTO dto3 = new TransactionRecordDTO(
                167875,
                "NL93ABNA0585619023",
                "Toy Greg Alysha",
                5429.0, -939.0, 6368.0
        );
        TransactionRecordDTOList.add(dto3);
        TransactionRecordDTO dto4 = new TransactionRecordDTO(
                112806,
                "NL69ABNA0433647324",
                "Book Peter de Vries",
                90.83, -10.91, 79.92
        );
        TransactionRecordDTOList.add(dto4);
        TransactionRecordDTO dto5 = new TransactionRecordDTO(
                112806,
                "NL93ABNA0585619023",
                "Book Richard Tyson",
                102.12, +45.87, 147.99
        );
        TransactionRecordDTOList.add(dto5);
        TransactionRecordDTO dto6 = new TransactionRecordDTO(
                183049,
                "NL69ABNA0433647324",
                "Book Arndt Thilo",
                86.66, +44.5, 131.16
        );
        TransactionRecordDTOList.add(dto6);
        TransactionRecordDTO dto7 = new TransactionRecordDTO(
                183356,
                "NL74ABNA0248990274",
                "Toy Jimmie Clarice",
                92.98, -46.65, 46.33
        );
        TransactionRecordDTOList.add(dto7);
        TransactionRecordDTO dto8 = new TransactionRecordDTO(
                139524,
                "NL43AEGO0773393871",
                "Flowers Jan Tyson",
                99.44, +41.23, 140.67
        );
        TransactionRecordDTOList.add(dto8);
        TransactionRecordDTO dto9 = new TransactionRecordDTO(
                179430,
                "NL93ABNA0585619023",
                "Clothes Dolores Kerensa",
                23.96, -27.43, -3.47
        );
        TransactionRecordDTOList.add(dto9);
        TransactionRecordDTO dto10 = new TransactionRecordDTO(
                141223,
                "NL93ABNA0585619023",
                "Book Mahala Kreszenz",
                94.25, +41.6, 135.85
        );
        TransactionRecordDTOList.add(dto10);
        return TransactionRecordDTOList;
    }

    List<TransactionRecordDTO> createInvalidReferenceTransactionRecordDTO() {
        List<TransactionRecordDTO> TransactionRecordDTOList = new ArrayList<>();
        TransactionRecordDTO dto = new TransactionRecordDTO(
                194261,
                "NL91RABO0315273637",
                "Book John Smith",
                21.6, -41.83, -20.23
        );
        TransactionRecordDTOList.add(dto);
        TransactionRecordDTO dto1 = new TransactionRecordDTO(
                112806,
                "NL27SNSB0917829871",
                "Clothes Irma Steven",
                91.23, +15.57, 106.8
        );
        TransactionRecordDTOList.add(dto1);
        TransactionRecordDTO dto2 = new TransactionRecordDTO(
                183049,
                "NL69ABNA0433647324",
                "Book Arndt Thilo",
                86.66, +44.5, 131.16
        );
        TransactionRecordDTOList.add(dto2);
        TransactionRecordDTO dto3 = new TransactionRecordDTO(
                112806,
                "NL74ABNA0248990274",
                "Toy Jimmie Clarice",
                92.98, -46.65, 46.33
        );
        TransactionRecordDTOList.add(dto3);
        TransactionRecordDTO dto4 = new TransactionRecordDTO(
                112806,
                "NL43AEGO0773393871",
                "Flowers Jan Tyson",
                99.44, +41.23, 140.67
        );
        TransactionRecordDTOList.add(dto4);
        return TransactionRecordDTOList;
    }

    List<TransactionRecordDTO> createInvalidEndBalanceTransactionRecordDTO() {
        List<TransactionRecordDTO> TransactionRecordDTOList = new ArrayList<>();
        TransactionRecordDTO dto = new TransactionRecordDTO(
                194261,
                "NL91RABO0315273637",
                "Book John Smith",
                21.6, -41.83, -20.23
        );
        TransactionRecordDTOList.add(dto);
        TransactionRecordDTO dto1 = new TransactionRecordDTO(
                112806,
                "NL27SNSB0917829871",
                "Clothes Irma Steven",
                91.23, +15.57, 106.8
        );
        TransactionRecordDTOList.add(dto1);
        TransactionRecordDTO dto2 = new TransactionRecordDTO(
                183049,
                "NL69ABNA0433647324",
                "Book Arndt Thilo",
                86.66, +44.5, 131.00
        );
        TransactionRecordDTOList.add(dto2);
        TransactionRecordDTO dto3 = new TransactionRecordDTO(
                183356,
                "NL74ABNA0248990274",
                "Toy Jimmie Clarice",
                92.98, -46.65, 139.63
        );
        TransactionRecordDTOList.add(dto3);
        TransactionRecordDTO dto4 = new TransactionRecordDTO(
                139524,
                "NL43AEGO0773393871",
                "Flowers Jan Tyson",
                99.44, +41.23, 140.67
        );
        TransactionRecordDTOList.add(dto4);
        return TransactionRecordDTOList;
    }
}
