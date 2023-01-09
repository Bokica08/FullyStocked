package com.bazi.fullystocked.Services;

import com.bazi.fullystocked.Models.Workers;
import java.util.*;

public interface WorkersService {
    Optional<Workers> assignLocation(Integer workerId, Integer locationId);
    List<Workers> findAllWithNoLocation();
}
