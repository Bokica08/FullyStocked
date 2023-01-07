package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.SqlViews.SuppliersReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersReportRepository extends JpaRepository<SuppliersReport, Integer> {

}
