package tech.buildrun.picpay.controller.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.buildrun.picpay.entity.Wallet;

@Validated
public record CreateWalletRequestDTO(
        @NotBlank String fullname,
        @NotBlank String cpfCnpj,
        @Email String email,
        @NotBlank String password,
        @NotNull Wallet.WalletType walletType) {

    public Wallet toEntity() {
        return Wallet.builder()
                .fullname(fullname)
                .cpfCnpj(cpfCnpj)
                .email(email)
                .password(password)
                .walletType(walletType)
                .build();
    }
}
