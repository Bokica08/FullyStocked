package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class managers extends User{
    public managers(String firstname, String lastname, String username, String email, String password) {
        super(firstname, lastname, username, email, password);
    }
}
