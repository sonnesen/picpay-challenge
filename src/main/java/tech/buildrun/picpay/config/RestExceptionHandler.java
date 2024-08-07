package tech.buildrun.picpay.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tech.buildrun.picpay.exception.PicPayException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final var invalidFields = e.getFieldErrors()
                .stream()
                .map(field -> new InvalidField(field.getField(), field.getDefaultMessage()))
                .toList();

        final var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Bad Request");
        pb.setDetail("The request contains invalid fields");
        pb.setProperty("invalid-fields", invalidFields);

        return pb;
    }

    private record InvalidField(String name, String message) {
    }
}
