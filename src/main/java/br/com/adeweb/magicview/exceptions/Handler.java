package br.com.adeweb.magicview.exceptions;

import br.com.adeweb.magicview.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> trataErro400(Exception ex) {
        HttpStatus codigo = HttpStatus.BAD_REQUEST;
        ErrorResponse error = new ErrorResponse(ex.getMessage(), codigo.value(), codigo.toString(), ex.getClass().getSimpleName(), null);
        return ResponseEntity.status(codigo).body(error);
    }

}
