package ie.atu.loan_service.client;

import ie.atu.loan_service.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(
        //name="users-service",
        //url="http://localhost:8080/api/user"
//)

@FeignClient(
        name="book-service",
        url = "${book.service.base-url}"
)

public interface BookClient {
    @GetMapping("/book/{id}")
    BookDTO getBookById(@PathVariable("id") long bookId);
}
