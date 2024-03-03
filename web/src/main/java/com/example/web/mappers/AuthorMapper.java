package com.example.web.mappers;

import com.example.web.model.Author;
import com.example.web.model.dto.AuthorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto authorToAuthorDto(Author author);

    Author authorDtoToAuthor(AuthorDto authorDto);
}