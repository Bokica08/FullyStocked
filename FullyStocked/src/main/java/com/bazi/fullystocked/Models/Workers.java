package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Workers extends User{
    @ManyToOne
    @JoinColumn(name = "locationid")
    private Locations location;

    public Workers(String firstname, String lastname, String username, String email, String password, Locations location) {
        super(firstname, lastname, username, email, password);
        this.location = location;
    }
    public Workers(String firstname, String lastname, String username, String email, String password) {
        super(firstname, lastname, username, email, password);
    }
}
