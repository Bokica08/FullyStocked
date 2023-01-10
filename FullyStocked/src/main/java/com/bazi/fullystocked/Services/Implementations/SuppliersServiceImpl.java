package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Categories;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.SqlViews.SuppliersReport;
import com.bazi.fullystocked.Models.Suppliers;
import com.bazi.fullystocked.Repositories.CategoriesRepository;
import com.bazi.fullystocked.Repositories.SuppliersReportRepository;
import com.bazi.fullystocked.Repositories.SuppliersRepository;
import com.bazi.fullystocked.Services.SuppliersService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SuppliersServiceImpl implements SuppliersService {
    private final SuppliersRepository suppliersRepository;
    private final SuppliersReportRepository suppliersReportRepository;

    public SuppliersServiceImpl(SuppliersRepository suppliersRepository, SuppliersReportRepository suppliersReportRepository) {
        this.suppliersRepository = suppliersRepository;
        this.suppliersReportRepository = suppliersReportRepository;
    }

    @Override
    public List<Suppliers> findAll() {
        return suppliersRepository.findAll();
    }

    @Override
    public Optional<SuppliersReport> findByIdReport(Integer id) {
        return suppliersReportRepository.findById(id);
    }

    @Override
    public List<SuppliersReport> findAllSuppliersReport() {
        return suppliersReportRepository.findAll();
    }

    @Override
    public Optional<Suppliers> findById(Integer id) {
        return suppliersRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Categories> findCategoriesBySupplier(Integer id) {
        Suppliers supplier=suppliersRepository.findById(id).orElseThrow(InvalidArgumentsException::new);
        return supplier.getCategoryList();
    }
}
