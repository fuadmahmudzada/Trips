package com.trips.mvc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.swing.*;
@NoRepositoryBean
public interface PageAndSortingRepository <Article, Id> extends CrudRepository<Article,Id> {
    Iterable<Article> fincdAll(Sort sort);
    Page<Article> findAll(Pageable pageable);
}
