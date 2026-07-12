package com.api.pathwise.entity;

import com.api.pathwise.dto.TestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "test_cases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "path_id")
    private EnumeratedPath enumeratedPath;

    private String title;

    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "test_data")
    private Map<String, Object> testData;

    @Column(name = "expected_result")
    private String expectedResult;

    @Enumerated(EnumType.STRING)
    private TestStatus status;
}
