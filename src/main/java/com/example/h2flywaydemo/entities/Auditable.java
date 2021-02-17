package com.example.h2flywaydemo.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@ToString
@MappedSuperclass
public abstract class Auditable {

    @Column(name = "created_on")
    protected Date createdOn;

    @Setter
    @NotNull
    @Column(name = "created_by")
    protected String createdBy;

    @PrePersist
    void initialize() {
        this.createdOn = new Date();
    }
}

