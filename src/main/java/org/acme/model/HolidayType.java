package org.acme.model;

import jakarta.persistence.*;

@Entity
@Table(name = "holidayType")
public class HolidayType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @ManyToOne
    @JoinColumn(name = "holiday_id")
    private Holiday holiday;

    public HolidayType(String type) {
        this.type = type;
    }
    public HolidayType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }
}
