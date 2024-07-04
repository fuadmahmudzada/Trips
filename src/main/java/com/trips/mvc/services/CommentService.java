package com.trips.mvc.services;

import com.trips.mvc.dtos.commentdtos.CommentCreateDto;
import com.trips.mvc.dtos.commentdtos.CommentDto;

import java.util.List;

public interface CommentService {

    void addComment(CommentCreateDto commentCreate, String email);
    List<CommentDto> getCommentsByArticleId(Long articleId);
}
