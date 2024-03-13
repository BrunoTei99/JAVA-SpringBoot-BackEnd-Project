package com.example.web.controller;

import com.example.web.model.dto.AuthorDto;
import com.example.web.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        logger.info("Fetching all authors");
        List<AuthorDto> authors;
        try {
            authors = authorService.getAllAuthors();
            logger.info("Retrieved {} authors", authors.size());
        } catch (Exception e) {
            logger.error("Error occurred while fetching all authors: {}", e.getMessage());
            throw e;
        }
        return authors;
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable Long id) {
        logger.info("Fetching author with ID {}", id);
        AuthorDto author;
        try {
            author = authorService.getAuthorById(id);
            if (author != null) {
                logger.info("Author with ID {} found", id);
            } else {
                logger.warn("Author with ID {} not found", id);
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching author with ID {}: {}", id, e.getMessage());
            throw e;
        }
        return author;
    }

    @PostMapping
    public void addNewAuthor(@RequestBody AuthorDto authorDto) {
        logger.info("Adding a new author: {}", authorDto);
        try {
            authorService.addNewAuthor(authorDto);
            logger.info("New author added successfully");
        } catch (Exception e) {
            logger.error("Error occurred while adding a new author: {}", e.getMessage());
            throw e;
        }
    }

    @PutMapping("/{id}")
    public void updateAuthor(@PathVariable Long id, @RequestBody AuthorDto updatedAuthorDto) {
        logger.info("Updating author with ID {}", id);
        try {
            authorService.updateAuthor(id, updatedAuthorDto);
            logger.info("Author with ID {} updated successfully", id);
        } catch (Exception e) {
            logger.error("Error occurred while updating author with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }
}
