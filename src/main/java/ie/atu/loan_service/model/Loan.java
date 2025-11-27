package ie.atu.loan_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name="loans")
@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String loanId;
    @NotBlank
    private String userId;
    @NotBlank
    private String bookId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate reminderDate;
}