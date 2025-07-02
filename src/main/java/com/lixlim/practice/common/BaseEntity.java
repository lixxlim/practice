package com.lixlim.practice.common;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreatedDate
    private Instant createdDtUtc;

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private Instant updatedDtUtc;

    @LastModifiedBy
    private String updatedBy;

    private Boolean isDeleted;

}
