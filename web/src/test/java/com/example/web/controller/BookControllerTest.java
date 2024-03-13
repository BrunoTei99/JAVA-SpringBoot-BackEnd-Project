package com.example.web.controller;
import com.example.web.model.dto.BookDto;
import com.example.web.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<BookDto> mockBooks = new ArrayList<>();
        when(bookService.getAllBooks()).thenReturn(mockBooks);

        ResponseEntity<List<BookDto>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    public void testGetBookById() {
        Long id = 1L;
        BookDto mockBook = new BookDto();
        when(bookService.getBookById(id)).thenReturn(mockBook);

        ResponseEntity<BookDto> response = bookController.getBookById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService, times(1)).getBookById(id);
    }

    @Test
    public void testAddNewBook() {
        BookDto bookDto = new BookDto();

        ResponseEntity<String> response = bookController.addNewBook(bookDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(bookService, times(1)).addNewBook(bookDto);
    }

    @Test
    public void testUpdateBook() {
        Long id = 1L;
        BookDto updatedBookDto = new BookDto();

        ResponseEntity<String> response = bookController.updateBook(id, updatedBookDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService, times(1)).updateBook(id, updatedBookDto);
    }

//    @Test
//    public void testDeleteBookById() {
//        Long id = 1L;
//
//        ResponseEntity<String> response = bookController.deleteBookById(id);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(bookService, times(1)).deleteBook(id);
//    }
}
