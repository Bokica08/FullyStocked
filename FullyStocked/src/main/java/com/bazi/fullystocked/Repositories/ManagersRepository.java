package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.Managers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagersRepository extends JpaRepository<Managers, Integer> {

}
