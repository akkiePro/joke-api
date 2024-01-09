package com.akki.jokeapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entity/Model class of Jokes table
 * @author Akash Prajapati
 */
@Entity
@Data
public class Jokes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jId;

    private String jType;

    private String joke;

}
