package com.example.web.model.dto;

import com.example.web.model.Author;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BookDto {

    private Long id;
    private String tittle;
    private String isbn;
    private String publisher;
    private BigDecimal price;
    private Boolean avaiability;
    private Integer stockAvaiable;
    private List<AuthorDto> authors;
    private List<LanguageDto> languages;

}