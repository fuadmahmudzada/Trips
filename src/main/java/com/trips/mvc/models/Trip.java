package com.trips.mvc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //    private String author;
    private String description;
    @Column(name = "content", length = 10000)
    private String content;
    private String imageUrl;
    private String backImageUrl;
    private Integer price;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;
    private String seoUrl;
}
