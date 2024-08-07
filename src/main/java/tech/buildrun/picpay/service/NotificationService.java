package tech.buildrun.picpay.service;

import tech.buildrun.picpay.client.dto.TransferMessageDTO;

public interface NotificationService {

    void sendNotification(TransferMessageDTO transferMessage);
}
