package com.example.demo.entity.sent;

import com.example.demo.entity.BaseMgnt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
public class SentEnvironmentalMgnt extends BaseMgnt {
    @Id
    private Long id;

    private String prop1;
    private String prop2;
    private String prop3;
}
