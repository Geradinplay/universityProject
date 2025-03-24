package org.acme.model;

import jakarta.persistence.*;
@NamedQueries({
        @NamedQuery(name = Profession.GET_ALL_ITEMS, query = "SELECT i FROM Profession i")
})

@Entity
@Table(name = "profession")
public class Profession {
    public static final String GET_ALL_ITEMS = "Profession.getAllProfession";
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

