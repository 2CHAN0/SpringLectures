package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExhandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e.getMessage());
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler()//파라미터와 동일한 exception이면 입력 생략 가능
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionhandler] ex", e.getMessage());
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionhandler] ex", e.getMessage());
        return new ErrorResult("EX", "내부 오류");
    }
}
