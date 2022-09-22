package vn.elca.training.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author gtn
 *
 */

@Getter
@Setter
public class ProjectDto {
    private Long id;


    private Long projectNumber;

    private String ProjectName;

    private String customer;

    private String groupLeader;

    private String employees;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


//    private Long leaderId;


    private Long version;


}
