package ie.atu.loan_service.client;

import ie.atu.loan_service.model.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(
        //name="notification-service",
        //url="http://localhost:8083/api/notification"
//)

@FeignClient(
        name="notification-service",
        url = "${notification.service.base-url}"
)

public interface NotificationClient {
    @PostMapping
    void sendNotification(@RequestBody NotificationDTO dto);
}
