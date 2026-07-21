package com.api.pathwise.repository;

import com.api.pathwise.entity.Flow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlowRepository extends JpaRepository<Flow, Long> {

    @Query("SELECT f FROM Flow f WHERE (:searchTerm IS NULL OR f.name LIKE %:searchTerm%)")
    Page<Flow> findAllWithPagination(
            @Param("searchTerm") String searchTerm,
            Pageable pageable
    );
}
