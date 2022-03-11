package com.example.demo.entity.draft;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class DraftEnvironment {
    @Id
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "id") // default report_id
    private DraftReport report;

    @OneToOne(fetch = FetchType.LAZY)
    private DraftEnvironmentalMgnt environmentalMgnt;
    // energy, water, waste, gas
}
