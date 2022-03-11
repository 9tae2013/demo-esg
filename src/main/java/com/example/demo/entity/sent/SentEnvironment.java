package com.example.demo.entity.sent;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class SentEnvironment {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SentEnvironmentalMgnt environmentalMgnt;
    // energy, water, waste, gas
}
