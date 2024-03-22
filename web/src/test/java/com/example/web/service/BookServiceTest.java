package com.example.web.service;
import com.example.web.mappers.BookMapper;
import com.example.web.model.Book;
import com.example.web.model.dto.BookDto;
import com.example.web.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetAllBooks() {
        // Arrange
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(new Book()));
        when(bookMapper.booksModelToBooksDto(anyList())).thenReturn(Collections.singletonList(new BookDto()));

        // Act
        var result = bookService.getAllBooks();

        // Assert
        assertFalse(result.isEmpty());
        verify(bookRepository, times(1)).findAll();
        verify(bookMapper, times(1)).booksModelToBooksDto(anyList());
    }

    @Test
    public void testGetBookById() {
        // Arrange
        Long id = 1L;
        when(bookRepository.findById(id)).thenReturn(Optional.of(new Book()));
        when(bookMapper.bookModelToBookDto(any())).thenReturn(new BookDto());

        // Act
        var result = bookService.getBookById(id);

        // Assert
        assertNotNull(result);
        verify(bookRepository, times(1)).findById(id);
        verify(bookMapper, times(1)).bookModelToBookDto(any());
    }

    @Test
    public void testAddNewBook() {
        // Arrange
        BookDto bookDto = new BookDto();

        // Act
        bookService.addNewBook(bookDto);

        // Assert
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateBook() {
        // Arrange
        Long id = 1L;
        BookDto updatedBookDto = new BookDto();
        Book existingBook = new Book();
        existingBook.setId(id);
        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));
        when(bookMapper.bookDtoToBookModel(updatedBookDto)).thenReturn(new Book());

        // Act
        bookService.updateBook(id, updatedBookDto);

        // Assert
        verify(bookRepository, times(1)).findById(id);
        verify(bookMapper, times(1)).bookDtoToBookModel(updatedBookDto);
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    public void testDeleteBook() {
        // Arrange
        Long id = 1L;
        Book existingBook = new Book();
        existingBook.setId(id);
        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));

        // Act
        bookService.deleteBook(id);

        // Assert
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).delete(existingBook);
    }
}
