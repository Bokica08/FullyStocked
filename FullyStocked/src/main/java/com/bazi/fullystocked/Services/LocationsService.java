package com.bazi.fullystocked.Services;


import com.bazi.fullystocked.Models.SqlViews.ArticlesAtLocationReport;

import java.util.*;

public interface LocationsService {
    List<ArticlesAtLocationReport> findAllArticlesAtLocation(Integer locationid);
}
