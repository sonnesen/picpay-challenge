package tech.buildrun.picpay.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tech.buildrun.picpay.controller.TransferApi;
import tech.buildrun.picpay.controller.dto.TransferRequestDTO;
import tech.buildrun.picpay.controller.dto.TransferResponseDTO;
import tech.buildrun.picpay.service.TransferService;

@RestController
@RequiredArgsConstructor
public class TransferController implements TransferApi {

    @NonNull
    private final TransferService transferService;

    public ResponseEntity<TransferResponseDTO> transfer(TransferRequestDTO body) {
        final var transfer = transferService.transfer(body);
        return ResponseEntity.status(HttpStatus.OK).body(transfer);
    }
}
