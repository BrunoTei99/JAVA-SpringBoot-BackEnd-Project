package com.example.web.mappers;

import com.example.web.model.Author;
import com.example.web.model.dto.AuthorDto;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface AuthorMapper {

    AuthorDto authorModelToAuthorDto(Author author);

    Author authorDtoToAuthorModel(AuthorDto authorDto);

    List<AuthorDto> authorsModelToAuthorsDto(List<Author> authors);
}

