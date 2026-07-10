package com.api.pathwise.repository;

import com.api.pathwise.entity.EnumeratedPath;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnumeratedPathRepository extends JpaRepository<EnumeratedPath, Long> {
}
