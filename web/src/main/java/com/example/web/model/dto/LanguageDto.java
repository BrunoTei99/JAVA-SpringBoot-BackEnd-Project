package com.example.web.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

//    @JsonIgnore
//    private List<BookDto> books;

}
