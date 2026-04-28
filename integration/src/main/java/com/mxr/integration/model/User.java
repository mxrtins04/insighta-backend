package com.mxr.integration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.UUID;
import com.fasterxml.uuid.Generators;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    UUID id;

    @PrePersist
    public void generateId() {
        this.id = Generators.timeBasedEpochGenerator().generate();
    }

    private String githubId;

    private String username;

    private String email;

    private String avatarUrl;

    private String role;

    private boolean isActive;

    private Instant lastLoginAt;

    @CreationTimestamp
    private Instant createdAt;
}
