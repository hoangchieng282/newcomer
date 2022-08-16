package vn.elca.training.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.elca.training.model.entity.Status;


@Getter
@Setter
public class ProjectStatusNotValidException extends RuntimeException{

    private final Status status;
    private String message;

    public ProjectStatusNotValidException(Status status){ this.status = status;}
}
