package com.example.demo.entity.sent;

import com.example.demo.entity.BaseReport;
import com.example.demo.entity.UserProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
public class SentReport extends BaseReport {
    @Id
    private Long id;

    private LocalDateTime approveDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile approveBy;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SentEnvironment environment;
    // social, governance
}
