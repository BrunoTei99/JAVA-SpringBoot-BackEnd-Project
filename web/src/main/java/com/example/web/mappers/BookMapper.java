package com.example.web.mappers;


import com.example.web.model.Book;
import com.example.web.model.dto.BookDto;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {

    BookDto bookModelToBookDto(Book book);

    Book bookDtoToBookModel(BookDto bookDto);

    List<BookDto> booksModelToBooksDto (List<Book> books);

    List<Book> booksDtoToBookModel(List<BookDto> booksDto);

}