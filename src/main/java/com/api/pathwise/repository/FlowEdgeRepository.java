package com.api.pathwise.repository;

import com.api.pathwise.entity.FlowEdge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowEdgeRepository extends JpaRepository<FlowEdge, Long> {
    List<FlowEdge> findByFlow_Id(Long flowId);
}
