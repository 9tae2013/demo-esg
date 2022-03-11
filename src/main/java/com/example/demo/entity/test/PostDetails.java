package com.example.demo.entity.test;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class PostDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdOn;

    @OneToOne(mappedBy = "details", fetch = FetchType.LAZY, optional = true)
    private Post post;

    //Getters and setters omitted for brevity
}
