package com.dansales.elife.elifeapi.models;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;

}
