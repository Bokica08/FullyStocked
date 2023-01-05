package com.bazi.fullystocked.Services;

import com.bazi.fullystocked.Models.Managers;
import com.bazi.fullystocked.Models.Suppliers;
import com.bazi.fullystocked.Models.User;
import com.bazi.fullystocked.Models.Workers;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    User login(String username, String password);
    Workers registerWorker(String firstname, String lastname, String username, String email, String password);
    Managers registerManager(String firstname, String lastname, String username, String email, String password);
    Suppliers registerSupplier(String firstname, String lastname, String username, String email, String password, String supplierInfo, String phone, String street, int streetNumber, String city);

}
