package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Locations;
import com.bazi.fullystocked.Models.SqlViews.ArticlesAtLocationReport;
import com.bazi.fullystocked.Repositories.ArticlesAtLocationRepository;
import com.bazi.fullystocked.Repositories.LocationsRepository;
import com.bazi.fullystocked.Services.LocationsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationsService {
    private final ArticlesAtLocationRepository articlesAtLocationRepository;
    private final LocationsRepository locationsRepository;

    public LocationServiceImpl(ArticlesAtLocationRepository articlesAtLocationRepository, LocationsRepository locationsRepository) {
        this.articlesAtLocationRepository = articlesAtLocationRepository;
        this.locationsRepository = locationsRepository;
    }

    @Override
    public List<ArticlesAtLocationReport> findAllArticlesAtLocation(Integer locationid) {
        return articlesAtLocationRepository.findAllByLocationid(locationid);
    }

    @Override
    public List<Locations> findAll() {
        return locationsRepository.findAll();
    }
}
