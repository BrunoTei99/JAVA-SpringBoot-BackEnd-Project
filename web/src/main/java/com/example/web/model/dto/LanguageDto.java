package com.example.web.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LanguageDto {
    private Long id;
    private String name;
    private String code;
    private String nativeName;
    private List<BookDto> books;


}
