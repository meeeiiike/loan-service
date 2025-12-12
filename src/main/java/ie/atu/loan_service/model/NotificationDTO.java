package ie.atu.loan_service.model;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class NotificationDTO {
    private String userId;
    private String notification;
    private String email;
    //private String bookTitle;
    private LocalDate dueDate;
    //private String type;
}