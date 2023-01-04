package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.SqlViews.ArticlesAtLocationReport;
import com.bazi.fullystocked.Repositories.ArticlesAtLocationRepository;
import com.bazi.fullystocked.Services.LocationsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationsService {
    private final ArticlesAtLocationRepository articlesAtLocationRepository;

    public LocationServiceImpl(ArticlesAtLocationRepository articlesAtLocationRepository) {
        this.articlesAtLocationRepository = articlesAtLocationRepository;
    }

    @Override
    public List<ArticlesAtLocationReport> findAllArticlesAtLocation(Integer locationid) {
        return articlesAtLocationRepository.findAllByLocationid(locationid);
    }
}
