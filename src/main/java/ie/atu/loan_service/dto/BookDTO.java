package ie.atu.loan_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private long bookId;
    private String bookName;
    private String bookAuthor;
    private String bookDPublisher;
}
