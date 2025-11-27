package ie.atu.loan_service.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor@AllArgsConstructor@Data
public class ExceptionDetails {
    private String fieldname;
    private String fieldValue;
}
