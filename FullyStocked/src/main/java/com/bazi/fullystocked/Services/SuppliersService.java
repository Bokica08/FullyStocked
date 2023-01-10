package com.bazi.fullystocked.Services;

import com.bazi.fullystocked.Models.Categories;
import com.bazi.fullystocked.Models.SqlViews.SuppliersReport;
import com.bazi.fullystocked.Models.Suppliers;

import java.util.*;

public interface SuppliersService {
    List<Suppliers> findAll();
    Optional<SuppliersReport> findByIdReport(Integer id);
    List<SuppliersReport> findAllSuppliersReport();
    Optional<Suppliers> findById(Integer id);
    List<Categories> findCategoriesBySupplier(Integer id);
}
