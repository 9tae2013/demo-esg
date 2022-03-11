package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseMgnt {
    private LocalDateTime updateDate;
    private LocalDateTime confirmDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile updateBy;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile confirmBy;
}
