package vn.elca.training.web.advice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.elca.training.model.exception.*;

import java.time.LocalDate;


@RestControllerAdvice
public class AdviceController {

    private static final Logger logger = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler({
            ProjectNumberAlreadyExistsException.class,
            ProjectStatusNotValidException.class,
            StartDateAfterEndDateException.class,
    })
    public ResponseEntity<String> badRequestHandler(RuntimeException e) {
        logger.error(HttpStatus.BAD_REQUEST .name() + ": ",e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.toString());
    }

    @ExceptionHandler({
            ProjectNotFoundException.class,
    })
    public ResponseEntity<String> notFoundHandler(RuntimeException e) {
        logger.error(HttpStatus.NOT_FOUND.name() + ": " ,e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.toString());
    }

    @ExceptionHandler({
            ConcurrentUpdateException.class,
    })
    public ResponseEntity<String> concurrentUpdateHandler(ConcurrentUpdateException e) {
        logger.error(HttpStatus.CONFLICT.name() + ": " ,e);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.toString());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> defaultErrorHandler(Exception e) {
        logger.error("unexpected error: ",e);
        return ResponseEntity
                .unprocessableEntity().body(e.toString());
    }
}
