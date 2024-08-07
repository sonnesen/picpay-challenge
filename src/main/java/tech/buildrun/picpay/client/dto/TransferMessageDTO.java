package tech.buildrun.picpay.client.dto;

import java.util.UUID;

import tech.buildrun.picpay.entity.Transfer;

public record TransferMessageDTO(UUID id, Long senderId, Long receiverId, double value) {

    public static TransferMessageDTO fromEntity(Transfer transfer) {
        return new TransferMessageDTO(transfer.getId(), transfer.getSender().getId(), transfer.getReceiver().getId(),
                transfer.getAmount().doubleValue());
    }
}
