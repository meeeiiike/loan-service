package ie.atu.loan_service.errorHandling;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDetails>> showErrorDetails(MethodArgumentNotValidException mae){
        List<ExceptionDetails> errorList = new ArrayList<>();
        for(FieldError fieldError : mae.getBindingResult().getFieldErrors()){
            errorList.add(new ExceptionDetails(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ExceptionDetails> showErrorDetails(FeignException feignException){
        ExceptionDetails errorDetails = new ExceptionDetails();
        errorDetails.setFieldname("[ERROR] ID ");
        errorDetails.setFieldValue(feignException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(DuplicateExceptionHandling.class)
    public ResponseEntity<ExceptionDetails> duplicateException(DuplicateExceptionHandling dupe){
        ExceptionDetails errorDetails = new ExceptionDetails();
        errorDetails.setFieldname("[ERROR] Loan ID ");
        errorDetails.setFieldValue(dupe.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> notFoundException(NotFoundException nfe){
        ExceptionDetails errorDetails = new ExceptionDetails();
        errorDetails.setFieldname("[ERROR] Loan ID ");
        errorDetails.setFieldValue(nfe.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }
}
