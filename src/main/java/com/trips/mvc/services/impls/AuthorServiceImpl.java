package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.authordtos.AuthorCreateDto;
import com.trips.mvc.dtos.authordtos.AuthorDetailDto;
import com.trips.mvc.dtos.authordtos.AuthorDto;
import com.trips.mvc.dtos.authordtos.AuthorUpdateDto;
import com.trips.mvc.helpers.SeoHelper;
import com.trips.mvc.models.Author;
import com.trips.mvc.repositories.ArticleRepository;
import com.trips.mvc.repositories.AuthorRepository;
import com.trips.mvc.repositories.CategoryRepository;
import com.trips.mvc.services.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public void add(AuthorCreateDto authorCreateDto) {
        try {
            Author author = new Author();
//            author.setUpdatedDate(new Date());
//            author.setCreatedDate(new Date());

            author.setAbout(authorCreateDto.getAbout());
            author.setName(authorCreateDto.getName());
            author.setImageUrl(authorCreateDto.getImageUrl());
            author.setDescription(authorCreateDto.getDescription());

            SeoHelper seoHelper = new SeoHelper();

            author.setSeoUrl(seoHelper.seoUrlHelper(authorCreateDto.getName()));
            author.setDeleted(false);

//            ArticleCategory category = categoryRepository.findById(articleCreateDto.getCategoryId()).get();
//            author.setArticleCategory(category);
            authorRepository.save(author);
        }catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<AuthorDto> getAuthors() {
        List<AuthorDto> authorDtoList = authorRepository.findAll().stream()
                .filter(x->x.isDeleted()== false)
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toList());
        return authorDtoList;
    }

    @Override
    public void updateAuthor(AuthorUpdateDto authorUpdateDto) {
        Author findAuthor = authorRepository.findById(authorUpdateDto.getId()).orElseThrow();

        findAuthor.setId(authorUpdateDto.getId());
        findAuthor.setName(authorUpdateDto.getName());
//        findArticle.setAuthor(articleUpdateDto.getAuthor());
        findAuthor.setAbout(authorUpdateDto.getAbout());

        SeoHelper seoHelper = new SeoHelper();
        findAuthor.setSeoUrl(seoHelper.seoUrlHelper(authorUpdateDto.getName()));

        findAuthor.setDescription(authorUpdateDto.getDescription());
//        findArticle.setUpdatedDate(new Date());
        findAuthor.setImageUrl(authorUpdateDto.getImageUrl());
//        findAuthor.setArticleCategory(category);
//        findArticle.setAuthor(author);
        authorRepository.saveAndFlush(findAuthor);
    }

    @Override
    public AuthorUpdateDto findUpdateAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        AuthorUpdateDto articleUpdateDto = modelMapper.map(author, AuthorUpdateDto.class);
        return articleUpdateDto;
    }

    @Override
    public void removeAuthor(Long authorId) {
        Author author =  authorRepository.findById(authorId).orElseThrow();
        author.setDeleted(true);
        authorRepository.save(author);
    }

    @Override
    public AuthorDetailDto authorDetail(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        AuthorDetailDto authorDetailDto = modelMapper.map(author, AuthorDetailDto.class);
        return authorDetailDto;
    }
}
