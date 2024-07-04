package com.trips.mvc.repositories;

import com.trips.mvc.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);
}