package com.example.demo.entity.draft;

import com.example.demo.entity.BaseMgnt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
public class DraftEnvironmentalMgnt extends BaseMgnt {
    @Id
    private Long id;

    private String prop1;
    private String prop2;
    private String prop3;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "id") // default environment_id from report_id
    private DraftEnvironment environment;
}
