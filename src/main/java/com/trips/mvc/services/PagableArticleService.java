package com.trips.mvc.services;

import com.trips.mvc.models.Article;
import org.springframework.data.domain.Page;

public interface PagableArticleService {
    Page<Article> getAll(int page, int size);
}
