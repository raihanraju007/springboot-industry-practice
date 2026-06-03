package com.raju.enterprise.springboot_industry_practice.model.entity;

import com.raju.enterprise.springboot_industry_practice.model.enums.CategoryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // Enum persisted as a readable String column ("ACTIVE"/"INACTIVE")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CategoryStatus status;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = CategoryStatus.ACTIVE;   // sensible default
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}