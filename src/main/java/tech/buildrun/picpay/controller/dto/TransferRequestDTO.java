package tech.buildrun.picpay.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record TransferRequestDTO(@DecimalMin("0.01") @NotNull BigDecimal amount, @NotNull Long payer,
        @NotNull Long payee) {

}
