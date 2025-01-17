package com.example.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movies")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(unique = true, nullable = false)
    private String title;
}
