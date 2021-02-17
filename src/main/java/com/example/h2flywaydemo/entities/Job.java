package com.example.h2flywaydemo.entities;

import com.example.h2flywaydemo.Constants;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
@Immutable
@Entity
@Table(schema=Job.SCHEMA)
public class Job extends Auditable {

    static final String SCHEMA = Constants.SCHEMA;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;


    private String description;
}
