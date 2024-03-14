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

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks_ReturnsBooks() {

    }

    @Test
    void getAllBooks_ReturnsNotFound_WhenNoBooks() {


    }

    @Test
    void getBookById_ReturnsBook_WhenExists() {

    }

    @Test
    void getBookById_ReturnsNotFound_WhenNotExists() {

    }

    @Test
    void addNewBook_ReturnsCreated_WhenSuccessful() {


    }

    @Test
    void addNewBook_ReturnsInternalServerError_WhenServiceFails() {

    }

    @Test
    void updateBook_ReturnsOk_WhenSuccessful() {

    }

    @Test
    void updateBook_ReturnsBadRequest_WhenIllegalArgumentExceptionThrown() {

    }

    @Test
    void updateBook_ReturnsInternalServerError_WhenServiceFails() {

    }}
