package tech.buildrun.picpay.service.impl;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tech.buildrun.picpay.controller.dto.CreateWalletRequestDTO;
import tech.buildrun.picpay.controller.dto.WalletDTO;
import tech.buildrun.picpay.exception.WalletAlreadyExistsException;
import tech.buildrun.picpay.repository.WalletRepository;
import tech.buildrun.picpay.service.WalletService;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    @NonNull
    private final WalletRepository walletRepository;

    @Override
    public WalletDTO createWallet(CreateWalletRequestDTO entity) {
        walletRepository.findByCpfCnpjOrEmail(entity.cpfCnpj(), entity.email())
                .ifPresent(wallet -> {
                    throw new WalletAlreadyExistsException("CPF/CNPJ or email already exists");
                });
        return WalletDTO.fromEntity(walletRepository.save(entity.toEntity()));
    }

}
