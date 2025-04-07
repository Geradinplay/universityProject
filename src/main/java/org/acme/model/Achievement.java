package org.acme.model;

import jakarta.persistence.*;

import java.util.List;

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
    @JoinColumn(name = "achievement")
    private String achievement;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

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
}
