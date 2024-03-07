package com.example.web.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "language")
@Builder
@Getter
@Setter
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String nativeName;

    @ManyToMany(
            mappedBy = "languages")
    private List<Book> books;

}
