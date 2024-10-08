package com.trips.mvc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    private String author;
    private String description;
    private String content;
    private String photoUrl;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;
    private String seoUrl;
    @ManyToOne
    private ArticleCategory articleCategory;

    @ManyToOne
    private Author author;
}
