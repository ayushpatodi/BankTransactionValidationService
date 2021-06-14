# BankTransactionValidationService
This serivce will validate the Bank Transaction Record file and will create a report for invalid records

## Approach
This is a spring boot application which uses libraries such as lombok. Using the framework and libraries will help in reducing need to write boiler plate code and focus on business logic.

Application expects 2 command line inputs:
- Path for input Bank Transtion Record File. (Supported FileType: CSV, JSON)
- Path for output Report file. (Supported FileType: CSV, JSON)

## Solution Details
- FileProcessingService class to deal with different file types.
- Logic to identify the duplicate references and invalid end balance can be implemented in a Java Class.

### Testing
- unit tests: JUnit + Mockito
- integration tests: Spring Boot Test

## Run locally
mvn clean install 

java -jar BankTransactionValidationService-0.0.1-SNAPSHOT.jar  <input-file-path> <output-file-path>
