package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

//@NamedQueries({ @NamedQuery(name = Student.GET_ALL_STUDENTS, query = "Select s from Student s"),
//        @NamedQuery(name = Student.GET_STUDENTS_BY_NAME, query = "Select s from Student s where s.ime = :name") })
@NamedQueries({
        @NamedQuery(name = Item.GET_ALL_ITEMS, query = "SELECT i FROM Item i"),
        @NamedQuery(name = Item.GET_ITEM_BY_ID, query = "SELECT i FROM Item i WHERE i.id=:id")
})

@Entity
@Table(name = "item")
public class Item {

    public static final String GET_ALL_ITEMS = "Item.getAllItems";
    public static final String GET_ITEM_BY_ID = "Item.getItemById";

    @Id
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    private Long id;
    @JsonProperty("name")
    @Column(name = "nameOfItem")
    private String nameOfItem;

    public Item() {
    }

    public Item(Long id, String nameOfItem) {
        this.id = id;
        this.nameOfItem = nameOfItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }


}
