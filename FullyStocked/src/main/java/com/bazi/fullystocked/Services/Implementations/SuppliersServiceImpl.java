package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Suppliers;
import com.bazi.fullystocked.Repositories.SuppliersRepository;
import com.bazi.fullystocked.Services.SuppliersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuppliersServiceImpl implements SuppliersService {
    private final SuppliersRepository suppliersRepository;

    public SuppliersServiceImpl(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    @Override
    public List<Suppliers> findAll() {
        return suppliersRepository.findAll();
    }
}
