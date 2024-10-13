package vn.jobhunters.service.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = IdInvalidException.class)
    public ResponseEntity<String> handleIdInvalidException(IdInvalidException idInvalidException) {
        return ResponseEntity.badRequest().body(idInvalidException.getMessage());
    }

}