package tech.buildrun.picpay.service.impl;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tech.buildrun.picpay.client.NotificationClient;
import tech.buildrun.picpay.client.dto.TransferMessageDTO;
import tech.buildrun.picpay.service.NotificationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @NonNull
    private final NotificationClient notificationService;

    @Override
    public void sendNotification(TransferMessageDTO transferMessage) {
        try {
            log.info("Sending notification: {}", transferMessage);
            final var response = notificationService.sendNotification(transferMessage);
            if (response.getStatusCode().isError()) {
                log.error("Error while sending notification: {}", response);
                return;
            }
            log.info("Notification sent: {}", transferMessage);
        } catch (Exception e) {
            log.error("Error while sending notification: {}", e);
        }
    }

}
