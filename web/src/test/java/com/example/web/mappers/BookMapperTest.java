package com.example.web.mappers;
import com.example.web.model.Book;
import com.example.web.model.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookMapperTest {

    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Test
    public void testBookModelToBookDto() {
        // Arrange
        Book book = new Book();
        book.setId(1L);
        book.setTittle("Test Book");

        // Act
        BookDto bookDto = mapper.bookModelToBookDto(book);

        // Assert
        assertEquals(book.getId(), bookDto.getId());
        assertEquals(book.getTittle(), bookDto.getTittle());
    }

    @Test
    public void testBookDtoToBookModel() {
        // Arrange
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTittle("Test Book");

        // Act
        Book book = mapper.bookDtoToBookModel(bookDto);

        // Assert
        assertEquals(bookDto.getId(), book.getId());
        assertEquals(bookDto.getTittle(), book.getTittle());
    }

    @Test
    public void testBooksModelToBooksDto() {
        // Arrange
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTittle("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTittle("Book 2");

        List<Book> books = Arrays.asList(book1, book2);

        // Act
        List<BookDto> bookDtos = mapper.booksModelToBooksDto(books);

        // Assert
        assertEquals(books.size(), bookDtos.size());
        assertEquals(books.get(0).getId(), bookDtos.get(0).getId());
        assertEquals(books.get(0).getTittle(), bookDtos.get(0).getTittle());
        assertEquals(books.get(1).getId(), bookDtos.get(1).getId());
        assertEquals(books.get(1).getTittle(), bookDtos.get(1).getTittle());
    }

    @Test
    public void testBooksDtoToBookModel() {
        // Arrange
        BookDto bookDto1 = new BookDto();
        bookDto1.setId(1L);
        bookDto1.setTittle("Book 1");

        BookDto bookDto2 = new BookDto();
        bookDto2.setId(2L);
        bookDto2.setTittle("Book 2");

        List<BookDto> bookDtos = Arrays.asList(bookDto1, bookDto2);

        // Act
        List<Book> books = mapper.booksDtoToBookModel(bookDtos);

        // Assert
        assertEquals(bookDtos.size(), books.size());
        assertEquals(bookDtos.get(0).getId(), books.get(0).getId());
        assertEquals(bookDtos.get(0).getTittle(), books.get(0).getTittle());
        assertEquals(bookDtos.get(1).getId(), books.get(1).getId());
        assertEquals(bookDtos.get(1).getTittle(), books.get(1).getTittle());
    }
}
