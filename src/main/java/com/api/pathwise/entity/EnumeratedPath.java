package com.api.pathwise.entity;

import com.api.pathwise.dto.enumpath.PathStep;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "enumerated_paths")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnumeratedPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_id")
    private Flow flow;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "path_sequence")
    private List<PathStep> pathSequence;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
