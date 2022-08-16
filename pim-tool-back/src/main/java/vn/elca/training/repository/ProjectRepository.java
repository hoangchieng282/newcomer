package vn.elca.training.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.Status;
import vn.elca.training.repository.custom.project.ProjectRepositoryCustom;

import java.util.List;

/**
 * @author vlp
 *
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, QuerydslPredicateExecutor<Project>, ProjectRepositoryCustom, PagingAndSortingRepository<Project, Long> {

    Boolean existsByProjectNumber(Long projectNumber);

    Page<Project> findByStatus(Status status, Pageable pageable);

    Page<Project> findAll(Pageable pageable);



}
