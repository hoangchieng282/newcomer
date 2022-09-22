package vn.elca.training.model.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectNumberAlreadyExistsException extends  RuntimeException{

    private final Long projectNumber;

    public ProjectNumberAlreadyExistsException(Long projectNumber){
        this.projectNumber = projectNumber;
    }
}
