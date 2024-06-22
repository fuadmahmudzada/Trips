//package com.trips.mvc.models;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.HashSet;
//import java.util.Set;
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "articleTag")
//public class ArticleTag {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//
//    @ManyToMany
//    @JoinTable(name = "article_articleTags",
//            joinColumns = @JoinColumn(name = "articleTag_id"),
//            inverseJoinColumns = @JoinColumn(name = "article_id"))
//    private Set<Article> articles = new HashSet();
//}
