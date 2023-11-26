package com.makesoftware.siga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class ClassPresenceList {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime timestamp;

    @OneToMany
    private List<ClassPresence> presenceList;
}
