package ie.atu.loan_service.client;

import ie.atu.loan_service.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="users-service", url="http://localhost:8080/api/user")
public interface UserClient {
    @GetMapping("/callUserService/{userId}")
    UserDTO getUserById(@PathVariable("userId") String userId);
}
