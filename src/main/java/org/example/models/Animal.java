package org.example.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


public abstract class Animal {

    private String name;
    private Date birthdate;

    public Animal(String name,Date birthdate){

        this.name = name;
        this.birthdate = birthdate;
    }

    public abstract void move();
}
