package tech.buildrun.picpay.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tech.buildrun.picpay.client.dto.TransferMessageDTO;
import tech.buildrun.picpay.controller.dto.TransferRequestDTO;
import tech.buildrun.picpay.controller.dto.TransferResponseDTO;
import tech.buildrun.picpay.entity.Transfer;
import tech.buildrun.picpay.entity.Wallet;
import tech.buildrun.picpay.exception.InsufficientBalanceException;
import tech.buildrun.picpay.exception.TransferNotAllowedForWalletTypeException;
import tech.buildrun.picpay.exception.WalletNotFoundException;
import tech.buildrun.picpay.repository.TransferRepository;
import tech.buildrun.picpay.repository.WalletRepository;
import tech.buildrun.picpay.service.AuthorizationService;
import tech.buildrun.picpay.service.NotificationService;
import tech.buildrun.picpay.service.TransferService;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    @NonNull
    private final AuthorizationService authorizationService;

    @NonNull
    private final NotificationService notificationService;

    @NonNull
    private final TransferRepository transferRepository;

    @NonNull
    private final WalletRepository walletRepository;

    @Transactional
    @Override
    public TransferResponseDTO transfer(TransferRequestDTO transferRequest) {
        final var sender = walletRepository.findById(transferRequest.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferRequest.payer()));

        final var receiver = walletRepository.findById(transferRequest.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferRequest.payee()));

        validateTransfer(sender, transferRequest);

        sender.debit(transferRequest.amount());
        receiver.credit(transferRequest.amount());

        final var transfer = Transfer.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(transferRequest.amount())
                .build();

        walletRepository.save(sender);
        walletRepository.save(receiver);
        transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(TransferMessageDTO.fromEntity(transfer)));

        return TransferResponseDTO.fromEntity(transfer);
    }

    private void validateTransfer(Wallet sender, TransferRequestDTO transferRequest) {
        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (sender.isBalanceInsufficient(transferRequest.amount())) {
            throw new InsufficientBalanceException(transferRequest.amount());
        }

        if (!authorizationService.isAuthorized(transferRequest)) {
            throw new InsufficientBalanceException(transferRequest.amount());
        }
    }

}
