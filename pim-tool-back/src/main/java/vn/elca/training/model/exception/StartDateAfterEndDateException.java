package vn.elca.training.model.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StartDateAfterEndDateException extends RuntimeException{
    private final int code = 404;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private String message;

    public StartDateAfterEndDateException(LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
