package com.bazi.fullystocked.Services;

import com.bazi.fullystocked.Models.Articles;
import java.util.*;

public interface ArticlesService {
    Optional<Articles> create(String description, String articlename, int maxquantityperlocation);
    Optional<Articles> create(String description, String articlename, String imageurl, int maxquantityperlocation);
    Optional<Articles> addToCategory(Integer articleId, Integer categoryId);
}
