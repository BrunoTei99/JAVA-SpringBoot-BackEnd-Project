package com.example.web.controller;

import com.example.web.model.dto.BookDto;
import com.example.web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/book")
public class BookController {

        private final BookService bookService;
        private static final Logger logger = LoggerFactory.getLogger(BookController.class);

        @Autowired
        public BookController(BookService bookService) {
                this.bookService = bookService;
        }

        @GetMapping
        public ResponseEntity<List<BookDto>> getAllBooks() {
                logger.info("Request received to retrieve all books.");
                List<BookDto> booksDto = bookService.getAllBooks();
                if (booksDto.isEmpty()) {
                        logger.info("No books found.");
                        return ResponseEntity.notFound().build();
                } else {
                        logger.info("Books retrieved successfully.");
                        return ResponseEntity.ok(booksDto);
                }
        }

        @GetMapping("/{id}")
        public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
                logger.info("Request received to retrieve book with id {}.", id);
                BookDto bookDto = bookService.getBookById(id);
                if (bookDto != null) {
                        logger.info("Book with id {} retrieved successfully.", id);
                        return ResponseEntity.ok(bookDto);
                } else {
                        logger.info("Book with id {} not found.", id);
                        return ResponseEntity.notFound().build();
                }
        }

        @PostMapping
        public ResponseEntity<String> addNewBook(@RequestBody BookDto bookDto) {
                logger.info("Request received to add a new book.");
                bookService.addNewBook(bookDto);
                logger.info("Book added successfully.");
                return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
        }

        @PutMapping("{id}")
        public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookDto updatedBookDTO) {
                logger.info("Request received to update book with id {}.", id);
                try {
                        bookService.updateBook(id, updatedBookDTO);
                        logger.info("Book with id {} updated successfully.", id);
                        return ResponseEntity.ok("Book updated successfully");
                } catch (IllegalArgumentException e) {
                        logger.error("Error occurred while updating book with id {}: {}", id, e.getMessage());
                        return ResponseEntity.badRequest().body(e.getMessage());
                }
        }


}
