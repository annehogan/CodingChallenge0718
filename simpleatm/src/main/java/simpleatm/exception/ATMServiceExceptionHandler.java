package simpleatm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import simpleatm.model.ServiceErrorCode;

/**
 * ATMServiceExceptionHandler - handles the {@link ServiceException}
 *
 * @Author Anne Hogan
 * @Copyright 2018
 */

@ControllerAdvice
@RestController
public class ATMServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ServiceErrorCode> handleServiceException(ServiceException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getServiceErrorCode(), HttpStatus.NOT_FOUND);
    }
}