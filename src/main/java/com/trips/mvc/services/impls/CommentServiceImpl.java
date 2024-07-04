package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.commentdtos.CommentCreateDto;
import com.trips.mvc.dtos.commentdtos.CommentDto;
import com.trips.mvc.models.Article;
import com.trips.mvc.models.Comment;
import com.trips.mvc.models.UserEntity;
import com.trips.mvc.repositories.ArticleRepository;
import com.trips.mvc.repositories.CommentRepository;
import com.trips.mvc.services.ArticleService;
import com.trips.mvc.services.CommentService;
import com.trips.mvc.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
@Autowired
private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;


    @Autowired
    private ModelMapper modelMapper;

    public void addComment(CommentCreateDto commentCreate, String email) {
        UserEntity user = userService.findByEmail(email);
        Article article = articleRepository.findById(commentCreate.getArticleId())
                .orElseThrow(() -> new RuntimeException("Article not found"));

        Comment newComment = new Comment();
        newComment.setContent(commentCreate.getContent());
        newComment.setUser(user);
        newComment.setArticle(article);

        commentRepository.save(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByArticleId(Long articleId) {
        List<Comment> result = commentRepository.findByArticleId(articleId);
        List<CommentDto> comments = result.stream().map(c ->{
            CommentDto comment = modelMapper.map(c, CommentDto.class);
            comment.setFullName(c.getUser().getFirstName() + " " + c.getUser().getLastName());
            return comment;
        }).collect(Collectors.toList());

        return comments;
    }
}