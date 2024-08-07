package tech.buildrun.picpay.service.impl;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tech.buildrun.picpay.client.AuthorizationClient;
import tech.buildrun.picpay.controller.dto.TransferRequestDTO;
import tech.buildrun.picpay.exception.PicPayException;
import tech.buildrun.picpay.service.AuthorizationService;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    @NonNull
    private final AuthorizationClient authorizationClient;

    @Override
    public boolean isAuthorized(TransferRequestDTO transferRequest) {
        final var response = authorizationClient.isAuthorized();

        if (response.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return response.getBody().authorized();
    }

}
