package com.github.ayushpatodi.banktransactionvalidationservice.constants;

//    *The class consist of the contants values that are used in the project
//    *
//    *@author Ayush Patodi

public class Constants {
    private Constants() {
    }

    //Constant fields
    public static final String JSON = "JSON";
    public static final String CSV = "CSV";
    public static final String JSON_SERVICE_IMPL_QUALIFIER = "jsonFileProcessingService";
    public static final String CSV_SERVICE_IMPL_QUALIFIER = "csvFileProcessingService";
    public static final String DECIMAL_FORMAT_PATTERN = "#.##";
    public static final String CSV_FILE_HEADER = "Reference, Description";

    //Constant for info logs
    public static final String REPORT_GENERATED_SUCCESSFULLY = "Records validated successfully, Report Generated";

    //Constact Error log message
    public static final String ERROR_LOG_INPUT_FILE_INVALID_FILE_TYPE = "Input File is not of supported file type. Only JSON or CSV file type allowed";
    public static final String EXCEPTION_LOG_INPUT_FILE_INVALID_FILE_TYPE = "Error in processing input file. Only JSON or CSV file type allowed.";

    public static final String ERROR_LOG_INPUT_FILE_FILE_TYPE_NOT_DETERMINED = "Error in determining the file type of input file. Please check if the file path is provided correctly or not.";
    public static final String EXCEPTION_LOG_INPUT_FILE_FILE_TYPE_NOT_DETERMINED = "Error in determining the file type of input file.";


    public static final String ERROR_LOG_OUTPUT_FILE_INVALID_FILE_TYPE = "Output File is not of supported file type. Only JSON or CSV file type allowed";
    public static final String EXCEPTION_LOG_OUTPUT_FILE_INVALID_FILE_TYPE = "Error in processing output file. Only JSON or CSV file type allowed.";

    public static final String ERROR_LOG_OUTPUT_FILE_FILE_TYPE_NOT_DETERMINED = "Error in determining the file type of output file. Please check if the file path is provided correctly or not.";
    public static final String EXCEPTION_LOG_OUTPUT_FILE_FILE_TYPE_NOT_DETERMINED = "Error in determining the file type of output file.";

    public static final String ERROR_LOG_CSV_FILE_NUMBER_FORMAT_ERROR = "Error while reading data from CSV file, One of the numeric field contains non-numeric data.";
    public static final String EXCEPTION_LOG_CSV_FILE_NUMBER_FORMAT_ERROR = "Error in reading the CSV file, data format issue.";

    public static final String ERROR_LOG_CSV_FILE_NOT_FOUND = "Error while reading the CSV file. File does not exist on the provided path";
    public static final String EXCEPTION_LOG_CSV_FILE_NOT_FOUND = "Exception while reading data from CSV file. File not found.";

    public static final String EXCEPTION_LOG_CSV_FILE_READ = "Exception while parsing CSV file.";

    public static final String EXCEPTION_LOG_CSV_FILE_WRITE = "Exception while writing the CSV file.";

    public static final String ERROR_LOG_JSON_FILE_NUMBER_FORMAT_ERROR = "Error while reading data from JSON file. One of the numeric field contains non-numeric data.";
    public static final String EXCEPTION_LOG_JSON_FILE_NUMBER_FORMAT_ERROR = "Error in reading the Json file, data format issue.";

    public static final String ERROR_LOG_JSON_FILE_NOT_FOUND = "Error while reading the JSON file. File does not exist on the provided path";
    public static final String EXCEPTION_LOG_JSON_FILE_NOT_FOUND = "Exception while reading data from Json file. File not found.";

    public static final String EXCEPTION_LOG_JSON_FILE_READ = "Exception while parsing JSON file.";

    public static final String EXCEPTION_LOG_JSON_FILE_WRITE = "Exception while writing the JSON file.";

}
