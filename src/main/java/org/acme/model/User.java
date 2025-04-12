package org.acme.model;

import jakarta.persistence.*;

import java.util.List;


@NamedQueries({
        @NamedQuery(name = User.GET_ALL_USERS, query = "SELECT i FROM User i"),
        @NamedQuery(name = User.GET_USER_BY_ID, query = "SELECT u FROM User u WHERE u.id = :id"),
})
@Entity
@Table(name = "users")
public class User {
    public static final String GET_ALL_USERS = "User.getAllUsers";
    public static final String GET_USER_BY_ID = "User.getUserById";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
private Long id;
private String nickname;
private int health;
private Long experience;
@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
@JoinTable(//Промежуточная таблица
        name = "user_items",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
)
private List<Item> items;
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "personal_data_id")
private PersonalData personalData;
@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
@JoinColumn(name = "profession_id")
private Profession profession;
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userId")
private List <Achievement> achievements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public Item addItem(Item item){
        items.add(item);
        return item;
    }

}
