package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.Exceptions.InvalidUserCredentialsException;
import com.bazi.fullystocked.Models.Exceptions.UserNotFoundException;
import com.bazi.fullystocked.Models.Exceptions.UsernameAlreadyExistsException;
import com.bazi.fullystocked.Models.Managers;
import com.bazi.fullystocked.Models.Suppliers;
import com.bazi.fullystocked.Models.User;
import com.bazi.fullystocked.Models.Workers;
import com.bazi.fullystocked.Repositories.ManagersRepository;
import com.bazi.fullystocked.Repositories.SuppliersRepository;
import com.bazi.fullystocked.Repositories.UsersRepository;
import com.bazi.fullystocked.Repositories.WorkersRepository;
import com.bazi.fullystocked.Services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UsersRepository usersRepository;
    private final WorkersRepository workersRepository;
    private final SuppliersRepository suppliersRepository;
    private final ManagersRepository managersRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl( UsersRepository usersRepository, WorkersRepository workersRepository, SuppliersRepository suppliersRepository, ManagersRepository managersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.workersRepository = workersRepository;
        this.suppliersRepository = suppliersRepository;
        this.managersRepository = managersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        User user = usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        if (passwordEncoder.matches(password, user.getUserpassword())) {
            return user;
        } else {
            throw new InvalidUserCredentialsException();
        }
    }

    @Override
    public Workers registerWorker(String firstname, String lastname, String username, String email, String password) {
        RegParamsCheck(firstname, lastname, username, email, password);
        return workersRepository.save(new Workers(firstname, lastname, username, email, passwordEncoder.encode(password)));
    }

    @Override
    public Managers registerManager(String firstname, String lastname, String username, String email, String password) {
        RegParamsCheck(firstname, lastname, username, email, password);
        return managersRepository.save(new Managers(firstname, lastname, username, email, passwordEncoder.encode(password)));
    }

    private void RegParamsCheck(String firstname, String lastname, String username, String email, String password) {
        if (firstname == null || firstname.isEmpty() || lastname == null || lastname.isEmpty() || username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        if (usersRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }
    }

    @Override
    public Suppliers registerSupplier(String firstname, String lastname, String username, String email, String password, String supplierInfo, String phone, String street, int streetNumber, String city) {
        if (firstname == null || firstname.isEmpty() || lastname == null || lastname.isEmpty() || username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        if (supplierInfo == null || supplierInfo.isEmpty() || phone == null || phone.isEmpty() || street == null || street.isEmpty() || city == null || city.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        if (usersRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }
        return suppliersRepository.save(new Suppliers(firstname, lastname, username, email, passwordEncoder.encode(password), supplierInfo, phone, street, streetNumber, city));
    }

}
