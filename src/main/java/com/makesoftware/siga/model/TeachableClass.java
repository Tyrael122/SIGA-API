package com.makesoftware.siga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class TeachableClass {
    @Id
    @GeneratedValue
    private Long id;

    private String classTitle;

    private LocalDateTime startTimestamp;

    @OneToOne
    private ClassPresenceList presenceList;
}
