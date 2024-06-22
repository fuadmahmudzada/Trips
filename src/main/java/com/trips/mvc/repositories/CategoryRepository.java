package com.trips.mvc.repositories;

import com.trips.mvc.models.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<ArticleCategory, Long> {

}
