package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameAndUserpassword(String username, String userpassword);
    Optional<User> findByUsername(String username);

}
