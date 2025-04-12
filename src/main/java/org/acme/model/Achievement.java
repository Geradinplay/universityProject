package org.acme.model;

import jakarta.persistence.*;
@NamedQueries({
        @NamedQuery(name = Achievement.GET_ALL_ACHIEVEMENT, query = "SELECT i FROM Achievement i")
})


@Entity
@Table(name = "achievements")
public class Achievement {
    public static final String GET_ALL_ACHIEVEMENT = "Achievement.getAllAchievement";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "achievement_seq")
    private Long id;
    private String achievement;
    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    private User userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
