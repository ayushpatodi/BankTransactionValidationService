package com.github.ayushpatodi.banktransactionvalidationservice.dto;

//    * DTO class for the Transaction Records
//    *
//    * @author Ayush Patodi

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRecordDTO {

    @CsvBindByName(column = "Reference")
    private long reference;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CsvBindByName(column = "AccountNumber")
    private String accountNumber;
    @CsvBindByName(column = "Description")
    private String description;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CsvBindByName(column = "Start Balance")
    private double startBalance;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CsvBindByName(column = "Mutation")
    private double mutation;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CsvBindByName(column = "End Balance")
    private double endBalance;
}
