package com.hello.user.bday.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class User {

    private @Id
    @GeneratedValue
    Long id;

    public User(Long id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public User(String name, Date dob) {
        this.name = name;
        this.dob = dob;
    }

    public User() {
    }

    @Column(unique = true)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date dob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
