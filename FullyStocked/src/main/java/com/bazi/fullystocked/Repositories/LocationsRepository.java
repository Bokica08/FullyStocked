package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Integer> {
    List<Locations> findAllByCityIgnoreCase(String city);
    List<Locations> findAllByLocationnameContainingIgnoreCase(String name);
}
