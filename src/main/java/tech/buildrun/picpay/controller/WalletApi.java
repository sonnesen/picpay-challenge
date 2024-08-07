package tech.buildrun.picpay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import tech.buildrun.picpay.controller.dto.CreateWalletRequestDTO;
import tech.buildrun.picpay.controller.dto.WalletDTO;

@RequestMapping("/wallets")
@Validated
public interface WalletApi {

    @PostMapping
    ResponseEntity<WalletDTO> createWallet(@RequestBody @Valid CreateWalletRequestDTO body);
}
