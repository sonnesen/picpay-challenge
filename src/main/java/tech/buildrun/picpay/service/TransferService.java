package tech.buildrun.picpay.service;

import tech.buildrun.picpay.controller.dto.TransferRequestDTO;
import tech.buildrun.picpay.controller.dto.TransferResponseDTO;

public interface TransferService {

    TransferResponseDTO transfer(TransferRequestDTO body);

}
