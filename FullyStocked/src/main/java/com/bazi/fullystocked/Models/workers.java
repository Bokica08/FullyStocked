package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class workers extends User{
    @ManyToOne
    @JoinColumn(name = "locationid")
    private locations location;

    public workers(String firstname, String lastname, String username, String email, String password, locations location) {
        super(firstname, lastname, username, email, password);
        this.location = location;
    }
}
