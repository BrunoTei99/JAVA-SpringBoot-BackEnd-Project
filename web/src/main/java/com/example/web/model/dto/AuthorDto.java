package com.example.web.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuthorDto {

    private Long id;
    private String name ;
    private Date bornDate;
    private String bornPlace;
    private Date diedDate;
    private String diedPlace;

}
