package com.example.web.controller;


import com.example.web.model.Book;
import com.example.web.model.dto.BookDto;
import com.example.web.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.PrivateKey;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
        private static final Logger logger = LogManager.getLogger(BookService.class);


        private final BookService bookService;

        @Autowired
        public BookController(BookService bookService) {
                this.bookService = bookService;
        }

        @GetMapping
        public ResponseEntity<List<BookDto>> getAllBooks() {

                logger.info("Request received to retrieve all books.");
                try {
                        List<BookDto> booksDto = bookService.getAllBooks();
                        if (booksDto.isEmpty()) {
                                logger.info("No books found.");
                                return new ResponseEntity<>(booksDto, HttpStatus.NOT_FOUND);
                        } else {
                                logger.info("Books retrieved successfully.");
                                return new ResponseEntity<>(booksDto, HttpStatus.OK);
                        }
                } catch (Exception e) {
                        logger.error("Error occurred while retrieving all books: {}", e.getMessage());
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        @GetMapping("/{id}")
        public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
                logger.info("Request received to retrieve book with id {}.", id);
                try {
                        BookDto bookDto = bookService.getBookById(id);
                        if (bookDto != null) {
                                logger.info("Book with id {} retrieved successfully.", id);
                                return new ResponseEntity<>(bookDto, HttpStatus.OK);
                        } else {
                                logger.info("Book with id {} not found.", id);
                                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                        }
                } catch (Exception e) {
                        logger.error("Error occurred while retrieving book with id {}: {}", id, e.getMessage());
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }
}
