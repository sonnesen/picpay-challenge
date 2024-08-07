package tech.buildrun.picpay.service;

import tech.buildrun.picpay.controller.dto.CreateWalletRequestDTO;
import tech.buildrun.picpay.controller.dto.WalletDTO;

public interface WalletService {

    WalletDTO createWallet(CreateWalletRequestDTO newWallet);
}
