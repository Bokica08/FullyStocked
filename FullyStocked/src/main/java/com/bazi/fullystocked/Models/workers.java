package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class workers extends User{
    @Column(nullable = false)
    @NotNull(message = "The worker must have location")
    private int locationid;

    public workers(String firstname, String lastname, String username, String email, String password, int locationid) {
        super(firstname, lastname, username, email, password);
        this.locationid = locationid;
    }
}
