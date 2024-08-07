package tech.buildrun.picpay.exception;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsufficientBalanceException extends PicPayException {

    private final BigDecimal amount;

    public InsufficientBalanceException(final BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        final var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Insufficient balance");
        pb.setDetail("The wallet does not have enough balance to transfer " + amount);
        return pb;
    }

}
