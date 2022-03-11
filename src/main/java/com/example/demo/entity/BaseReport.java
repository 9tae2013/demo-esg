package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseReport {
    private String asOfYear;
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
}
