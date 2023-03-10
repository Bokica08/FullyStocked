package com.bazi.fullystocked.Services;


import com.bazi.fullystocked.Models.DTO.LocationAnalysisDTO;
import com.bazi.fullystocked.Models.Locations;
import com.bazi.fullystocked.Models.SqlViews.ArticlesAtLocationReport;

import java.util.*;

public interface LocationsService {
    List<ArticlesAtLocationReport> findAllArticlesAtLocation(Integer locationid);
    List<Locations> findAll();
    List<LocationAnalysisDTO> getLocationAnalysis();
}
