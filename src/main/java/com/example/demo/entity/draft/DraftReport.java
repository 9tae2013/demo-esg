package com.example.demo.entity.draft;

import com.example.demo.entity.BaseReport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedEntityGraph(
        name = "draftReport.fetch_all",
        attributeNodes = @NamedAttributeNode(value = "environment", subgraph = "environment"),
        subgraphs = {
                @NamedSubgraph(
                        name = "environment",
                        attributeNodes = @NamedAttributeNode(value = "environmentalMgnt", subgraph = "environmentalMgnt")
                ),
                @NamedSubgraph(
                        name = "environmentalMgnt",
                        attributeNodes = {
                                @NamedAttributeNode("updateBy"),
                                @NamedAttributeNode("confirmBy")
                        }
                )
        }
)
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
public class DraftReport extends BaseReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private LocalDateTime statusDate;

    @OneToOne(fetch = FetchType.LAZY)
    private DraftEnvironment environment;
    // social, governance
}
