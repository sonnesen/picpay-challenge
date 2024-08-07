package tech.buildrun.picpay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import tech.buildrun.picpay.controller.dto.TransferRequestDTO;
import tech.buildrun.picpay.controller.dto.TransferResponseDTO;

public interface TransferApi {

    @PostMapping("/transfers")
    ResponseEntity<TransferResponseDTO> transfer(@RequestBody @Valid TransferRequestDTO body);
}
