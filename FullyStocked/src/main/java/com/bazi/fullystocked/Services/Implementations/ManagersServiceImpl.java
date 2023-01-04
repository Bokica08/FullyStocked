package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Managers;
import com.bazi.fullystocked.Repositories.ManagersRepository;
import com.bazi.fullystocked.Services.ManagersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagersServiceImpl implements ManagersService {
    private final ManagersRepository managersRepository;

    public ManagersServiceImpl(ManagersRepository managersRepository) {
        this.managersRepository = managersRepository;
    }

    @Override
    public List<Managers> findAll() {
        return managersRepository.findAll();
    }
}
