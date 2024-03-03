package com.example.web.model.dto;

import com.example.web.model.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AuthorDto {

    private Long id;
    private String name ;
    private Date bornDate;
    private String bornPlace;
    private Date diedDate;
    private String diedPlace;
    private List<BookDto> books ;

}
