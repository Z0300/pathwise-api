package com.api.pathwise.repository;

import com.api.pathwise.entity.FlowNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowNodeRepository extends JpaRepository<FlowNode, Long> {
}
