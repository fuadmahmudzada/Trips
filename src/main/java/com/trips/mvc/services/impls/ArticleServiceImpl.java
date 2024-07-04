package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.articledtos.*;
import com.trips.mvc.dtos.authordtos.AuthorDto;

import com.trips.mvc.dtos.authordtos.AuthorHomeDto;
import com.trips.mvc.dtos.categorydtos.CategoryHomeDto;
import com.trips.mvc.helpers.SeoHelper;
import com.trips.mvc.models.Article;
import com.trips.mvc.models.ArticleCategory;
import com.trips.mvc.models.Author;
import com.trips.mvc.repositories.ArticleRepository;
import com.trips.mvc.repositories.AuthorRepository;
import com.trips.mvc.repositories.CategoryRepository;
import com.trips.mvc.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public void add(ArticleCreateDto articleCreateDto) {
        try {
            Article article = new Article();
            article.setUpdatedDate(new Date());
            article.setCreatedDate(new Date());
//            article.setAuthor(articleCreateDto.getAuthor());
            article.setContent(articleCreateDto.getContent());
            article.setName(articleCreateDto.getName());
            article.setPhotoUrl(articleCreateDto.getPhotoUrl());
            SeoHelper seoHelper = new SeoHelper();
            article.setDeleted(false);
            article.setSeoUrl(seoHelper.seoUrlHelper(articleCreateDto.getName()));
            article.setDescription(articleCreateDto.getDescription());
            ArticleCategory category = categoryRepository.findById(articleCreateDto.getCategoryId()).get();
            article.setArticleCategory(category);
//            System.out.println("1" + articleCreateDto.getAuthorId());
//            System.out.println("2" + authorRepository.findById(articleCreateDto.getAuthorId()).get());
            Author author = authorRepository.findById(articleCreateDto.getAuthorId()).orElseThrow();
            article.setAuthor(author);
            articleRepository.save(article);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateArticle(ArticleUpdateDto articleUpdateDto) {
        Article findArticle = articleRepository.findById(articleUpdateDto.getId()).orElseThrow();
        ArticleCategory category = categoryRepository.findById(articleUpdateDto.getCategoryId()).orElseThrow();
        Author author = authorRepository.findById(articleUpdateDto.getAuthor().getId()).orElseThrow();
        findArticle.setId(articleUpdateDto.getId());
        findArticle.setName(articleUpdateDto.getName());
//        findArticle.setAuthor(articleUpdateDto.getAuthor());
        findArticle.setContent(articleUpdateDto.getContent());

        SeoHelper seoHelper = new SeoHelper();
        findArticle.setSeoUrl(seoHelper.seoUrlHelper(articleUpdateDto.getName()));

        findArticle.setDescription(articleUpdateDto.getDescription());
        findArticle.setUpdatedDate(new Date());
        findArticle.setPhotoUrl(articleUpdateDto.getPhotoUrl());
        findArticle.setArticleCategory(category);
        findArticle.setAuthor(author);
//        findArticle.setAuthor(author);
        articleRepository.saveAndFlush(findArticle);
    }
    @Override
    public ArticleDetailDto getDetail(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        ArticleDetailDto result = modelMapper.map(article, ArticleDetailDto.class);
        return result;
    }

    @Override
    public ArticleUpdateDto findUpdateArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        ArticleUpdateDto articleUpdateDto = modelMapper.map(article, ArticleUpdateDto.class);
        return articleUpdateDto;
    }

    @Override
    public List<ArticleDto> getArticles() {
        List<ArticleDto> articleDtoList = articleRepository.findAll().stream()
                .filter(x -> x.isDeleted() == false)
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
        return articleDtoList;
    }

    @Override
    public void removeArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        article.setDeleted(true);
        articleRepository.save(article);
    }

    @Override
    public ArticleDetailDto articleDetail(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        ArticleDetailDto articleDetailDto = modelMapper.map(article, ArticleDetailDto.class);
        return articleDetailDto;
    }

    @Override
    public List<ArticleHomeDto> getHomeArticles() {
        List<Article> articleList = articleRepository.findAll().stream()
                .filter(x -> !x.isDeleted()).toList();
        List<ArticleHomeDto> articleHomeDtoList = new ArrayList<>();

        for (Article article : articleList) {
            ArticleHomeDto dto = new ArticleHomeDto();
            dto.setId(article.getId());
            dto.setName(article.getName());
            dto.setDescription(article.getDescription());
            dto.setPhotoUrl(article.getPhotoUrl());
            dto.setCreatedDate(article.getCreatedDate());
            dto.setSeoUrl(article.getSeoUrl());

            AuthorDto authorDto = new AuthorDto();
            authorDto.setId(article.getAuthor().getId());
            authorDto.setName(article.getAuthor().getName());
            authorDto.setImageUrl(article.getAuthor().getImageUrl());
            authorDto.setAbout(article.getAuthor().getAbout());
            authorDto.setSeoUrl(article.getAuthor().getSeoUrl());

            dto.setAuthorDto(authorDto);

            articleHomeDtoList.add(dto);
        }
        return articleHomeDtoList;
    }

    @Override
    public Page<ArticleHomeDto> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Article> articleList = articleRepository.findAll().stream()
                .filter(x -> !x.isDeleted()).toList();
        List<ArticleHomeDto> articleHomeDtoList = new ArrayList<>();
        List<ArticleHomeDto> list;
        for (Article article : articleList) {
            ArticleHomeDto dto = new ArticleHomeDto();
            dto.setId(article.getId());
            dto.setName(article.getName());
            dto.setDescription(article.getDescription());
            dto.setPhotoUrl(article.getPhotoUrl());
            dto.setCreatedDate(article.getCreatedDate());
            dto.setSeoUrl(article.getSeoUrl());

            AuthorDto authorDto = new AuthorDto();
            authorDto.setId(article.getAuthor().getId());
            authorDto.setName(article.getAuthor().getName());
            authorDto.setImageUrl(article.getAuthor().getImageUrl());
            authorDto.setAbout(article.getAuthor().getAbout());
            authorDto.setSeoUrl(article.getAuthor().getSeoUrl());

            dto.setAuthorDto(authorDto);

            articleHomeDtoList.add(dto);
        }
        if (articleHomeDtoList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, articleHomeDtoList.size());
            list = articleHomeDtoList.subList(startItem, toIndex);
        }

        Page<ArticleHomeDto> bookPage
                = new PageImpl<ArticleHomeDto>(list, PageRequest.of(currentPage, pageSize), articleHomeDtoList.size());

        return bookPage;
    }

    @Override
    public AuthorHomeDto articleAuthor(Long id) {
        Author author = articleRepository.findById(id).orElseThrow().getAuthor();
        AuthorHomeDto authorHomeDto = modelMapper.map(author, AuthorHomeDto.class);
        return authorHomeDto;
    }

    @Override
    public List<ArticleHomeDto> getCategoryArticles(Long id/*umumi blog sehifesindeki articlenin id sidir bu*/) {
        List<Article> articleList = articleRepository.findAll().stream()
                .filter(x -> !x.isDeleted() && Objects.equals(x.getArticleCategory().getId()/*basadaki articlenin hazirkinin idsi*/, id)).toList();
        //niye equals isletdim
        List<ArticleHomeDto> categoryArticlesDtoList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleHomeDto dto = new ArticleHomeDto();
            dto.setId(article.getId());
            dto.setName(article.getName());
            dto.setDescription(article.getDescription());
            dto.setPhotoUrl(article.getPhotoUrl());
            dto.setCreatedDate(article.getCreatedDate());
            dto.setSeoUrl(article.getSeoUrl());
            CategoryHomeDto categoryHomeDto = new CategoryHomeDto();
            categoryHomeDto.setName(article.getArticleCategory().getName());
            dto.setCategoryHomeDto(categoryHomeDto);
            AuthorDto authorDto = new AuthorDto();
            authorDto.setId(article.getAuthor().getId());
            authorDto.setName(article.getAuthor().getName());
            authorDto.setImageUrl(article.getAuthor().getImageUrl());
            authorDto.setAbout(article.getAuthor().getAbout());
            authorDto.setSeoUrl(article.getAuthor().getSeoUrl());

            dto.setAuthorDto(authorDto);

            categoryArticlesDtoList.add(dto);
        }
        return categoryArticlesDtoList;
    }

    @Override
    public List<ArticleHomeDto> getAuthorArticles(Long id) {
        List<Article> articleList = articleRepository.findAll().stream()
                .filter(x -> !x.isDeleted() && Objects.equals(x.getAuthor().getId()/*basadaki articlenin hazirkinin idsi*/, id)).toList();
        //niye equals isletdim
        List<ArticleHomeDto> authorArticleDtoList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleHomeDto dto = new ArticleHomeDto();
            dto.setId(article.getId());
            dto.setName(article.getName());
            dto.setDescription(article.getDescription());
            dto.setPhotoUrl(article.getPhotoUrl());
            dto.setCreatedDate(article.getCreatedDate());
            dto.setSeoUrl(article.getSeoUrl());
            CategoryHomeDto categoryHomeDto = new CategoryHomeDto();
            categoryHomeDto.setName(article.getArticleCategory().getName());
            dto.setCategoryHomeDto(categoryHomeDto);
            AuthorDto authorDto = new AuthorDto();
            authorDto.setId(article.getAuthor().getId());
            authorDto.setName(article.getAuthor().getName());
            authorDto.setImageUrl(article.getAuthor().getImageUrl());
            authorDto.setAbout(article.getAuthor().getAbout());
            authorDto.setSeoUrl(article.getAuthor().getSeoUrl());

            dto.setAuthorDto(authorDto);

            authorArticleDtoList.add(dto);
        }
        return authorArticleDtoList;

    }


//    @Override
//    public Page<ArticleDto> getAll(int page, int size) {
//        logger.info("Page PROMOTIONS");
//        PageRequest pageRequest = PageRequest.of(page, size);
//        Page<Article> articles = this.articleRepository.findAll(pageRequest);
//        Page<ArticleDto> articleDtos = articles.map(ModelMapper.INSTANCE::toDto);
//        return promotionDtos;
//    }
}


