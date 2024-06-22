package com.trips.mvc.repositories;

import com.trips.mvc.models.ArticleCategory;
import com.trips.mvc.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
