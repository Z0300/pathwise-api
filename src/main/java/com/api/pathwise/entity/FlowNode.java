package com.api.pathwise.entity;

import com.api.pathwise.entity.enumeration.NodeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "flow_nodes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlowNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_id")
    private Flow flowId;

    @Column(name = "node_key")
    private String nodeKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "node_type")
    private NodeType nodeType;

    private String label;
}



