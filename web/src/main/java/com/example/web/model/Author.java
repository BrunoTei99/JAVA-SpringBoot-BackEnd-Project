package com.example.web.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
@Builder
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name ;
    private Date bornDate;
    private String bornPlace;
    private Date diedDate;
    private String diedPlace;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();


}
