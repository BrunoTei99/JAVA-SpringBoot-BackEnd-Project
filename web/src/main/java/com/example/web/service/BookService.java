package com.example.web.service;


import com.example.web.mappers.BookMapper;
import com.example.web.model.Book;
import com.example.web.model.dto.BookDto;
import com.example.web.repository.BookRepository;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private static final Logger logger = LogManager.getLogger(BookService.class);

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        try {
            logger.info("Fetching all books.");
            List<BookDto> booksDto = bookMapper.booksModelToBooksDto(bookRepository.findAll());
            logger.info("Fetched {} books.", booksDto.size());
            return booksDto;
        }catch (Exception e) {
            logger.error("An error occurred while fetching all books: {}", e.getMessage());
            throw e;
        }
    }

    public BookDto getBookById(Long id) {
        try {
            logger.info("Fetching book with id {}.", id);

            Optional<Book> optionalBook = bookRepository.findById(id);
            if (optionalBook.isPresent()) {
                logger.info("Book with id {} found.", id);
                return bookMapper.bookModelToBookDto(optionalBook.get());
            } else {
                logger.warn("Book with id {} not found.", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("An error occurred while fetching book with id {}: {}", id, e.getMessage());
            throw e;
        }

    }

}

