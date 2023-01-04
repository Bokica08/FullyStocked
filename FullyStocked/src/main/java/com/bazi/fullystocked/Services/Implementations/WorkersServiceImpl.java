package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.Locations;
import com.bazi.fullystocked.Models.Workers;
import com.bazi.fullystocked.Repositories.LocationsRepository;
import com.bazi.fullystocked.Repositories.WorkersRepository;
import com.bazi.fullystocked.Services.WorkersService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkersServiceImpl implements WorkersService {
    private final WorkersRepository workersRepository;
    private final LocationsRepository locationsRepository;

    public WorkersServiceImpl(WorkersRepository workersRepository, LocationsRepository locationsRepository) {
        this.workersRepository = workersRepository;
        this.locationsRepository = locationsRepository;
    }

    @Override
    public Optional<Workers> assignLocation(Integer workerId, Integer locationId) {
        Workers worker=workersRepository.findById(workerId).orElseThrow(InvalidArgumentsException::new);
        Locations location=locationsRepository.findById(locationId).orElseThrow(InvalidArgumentsException::new);
        worker.setLocation(location);
        return Optional.of(worker);
    }
}
