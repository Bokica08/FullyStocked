package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.Invoices;
import com.bazi.fullystocked.Models.Locations;
import com.bazi.fullystocked.Models.User;
import com.bazi.fullystocked.Models.Workers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface WorkersRepository extends JpaRepository<Workers, Integer> {
    List<Workers> findAllByLocation(Locations location);
    List<Workers> findAllByLocationIsNull();

}
