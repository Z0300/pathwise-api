package com.api.pathwise.repository;

import com.api.pathwise.entity.FlowNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowNodeRepository extends JpaRepository<FlowNode, Long> {
    List<FlowNode> findByFlow_Id(Long flowId);
}
