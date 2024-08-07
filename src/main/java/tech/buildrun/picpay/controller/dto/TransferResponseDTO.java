package tech.buildrun.picpay.controller.dto;

import java.util.UUID;

import tech.buildrun.picpay.entity.Transfer;

public record TransferResponseDTO(UUID id, Long sender, Long receiver, double amount) {

    public static TransferResponseDTO fromEntity(Transfer transfer) {
        return new TransferResponseDTO(transfer.getId(), transfer.getSender().getId(), transfer.getReceiver().getId(),
                transfer.getAmount().doubleValue());
    }
}
