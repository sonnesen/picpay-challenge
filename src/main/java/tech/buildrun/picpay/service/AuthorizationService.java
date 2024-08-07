package tech.buildrun.picpay.service;

import tech.buildrun.picpay.controller.dto.TransferRequestDTO;

public interface AuthorizationService {

    boolean isAuthorized(TransferRequestDTO transferRequest);
}
