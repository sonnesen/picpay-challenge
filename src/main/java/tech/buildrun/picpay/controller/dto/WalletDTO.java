package tech.buildrun.picpay.controller.dto;

import tech.buildrun.picpay.entity.Wallet;

public record WalletDTO(String fullname, String cpfCnpj, String email, Wallet.WalletType walletType) {

    public static WalletDTO fromEntity(Wallet entity) {
        return new WalletDTO(entity.getFullname(), entity.getCpfCnpj(), entity.getEmail(), entity.getWalletType());
    }
}
