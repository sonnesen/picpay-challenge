package tech.buildrun.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tech.buildrun.picpay.client.dto.TransferMessageDTO;

@FeignClient(name = "notification", url = "${client.notification-service.url}")
public interface NotificationClient {

    @PostMapping("/notifications")
    ResponseEntity<Void> sendNotification(@RequestBody TransferMessageDTO transferMessage);
}
