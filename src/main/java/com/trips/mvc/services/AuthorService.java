package com.trips.mvc.services;

import com.trips.mvc.dtos.articledtos.ArticleCreateDto;
import com.trips.mvc.dtos.articledtos.ArticleDetailDto;
import com.trips.mvc.dtos.authordtos.AuthorCreateDto;
import com.trips.mvc.dtos.authordtos.AuthorDetailDto;
import com.trips.mvc.dtos.authordtos.AuthorDto;
import com.trips.mvc.dtos.authordtos.AuthorUpdateDto;

import java.util.List;

public interface AuthorService {
    void add(AuthorCreateDto authorCreateDto);
    List<AuthorDto> getAuthors();
    void updateAuthor(AuthorUpdateDto authorDto);
    AuthorUpdateDto findUpdateAuthor(Long id);
    void removeAuthor(Long authorId);
    AuthorDetailDto authorDetail(Long id);
}
