package com.example.web.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.web.mappers.BookMapper;
import com.example.web.model.Book;
import com.example.web.model.dto.BookDto;
import com.example.web.repository.BookRepository;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {

    }

    @Test
    void testGetBookById() {

    }

    @Test
    void testAddNewBook() {

    }

    @Test
    void testUpdateBook() {

    }

    @Test
    void testDeleteBook() {

    }
}
