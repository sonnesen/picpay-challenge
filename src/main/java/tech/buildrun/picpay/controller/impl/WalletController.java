package tech.buildrun.picpay.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tech.buildrun.picpay.controller.WalletApi;
import tech.buildrun.picpay.controller.dto.CreateWalletRequestDTO;
import tech.buildrun.picpay.controller.dto.WalletDTO;
import tech.buildrun.picpay.service.WalletService;

@RestController
@RequiredArgsConstructor
public class WalletController implements WalletApi {

    @NonNull
    private final WalletService walletService;

    @Override
    public ResponseEntity<WalletDTO> createWallet(CreateWalletRequestDTO body) {
        final var createdWallet = walletService.createWallet(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWallet);
    }
}
