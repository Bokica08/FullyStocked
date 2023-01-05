package com.bazi.fullystocked.Models;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    @Column(nullable = false)
    @NotNull(message = "The user must have an first name")
    @NotEmpty(message = "The user must have an first name")
    private String firstname;
    @Column(nullable = false)
    @NotNull(message = "The user must have an last name")
    @NotEmpty(message = "The user must have an last name")
    private String lastname;
    @Column(nullable = false)
    @NotNull(message = "The user must have an username")
    @NotEmpty(message = "The user must have an username")
    private String username;
    @Column(nullable = false)
    @NotNull(message = "The user must have an email")
    @NotEmpty(message = "The user must have an email")
    private String email;
    @Column(nullable = false)
    @NotNull(message = "The user must have an password")
    @NotEmpty(message = "The user must have an password")
    private String userpassword;

    public User(String firstname, String lastname, String username, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.userpassword = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userpassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
