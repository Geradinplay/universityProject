package org.acme.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profession")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    private Long id;

    private String nameOfProfession;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfProfession() {
        return nameOfProfession;
    }

    public void setNameOfProfession(String nameOfProfession) {
        this.nameOfProfession = nameOfProfession;
    }
}

