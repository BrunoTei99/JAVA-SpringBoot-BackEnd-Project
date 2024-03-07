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

    public void addNewBook(BookDto bookDto){
        try{
            logger.info("adding a new book.");
            Book book = bookMapper.bookDtoToBookModel(bookDto);
            bookRepository.save(book);
            logger.info("Book added successfully.");
        }catch (Exception e) {
            logger.error("An error occurred while adding a new book: {}", e.getMessage());
            throw e;
        }
    }

    public void updateBook(Long id, BookDto updatedBookDTO) {
        try {
            logger.info("Updating book with id {}.", id);
            Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
            Book updatedBook = bookMapper.bookDtoToBookModel(updatedBookDTO);
            updatedBook.setId(existingBook.getId());
            bookRepository.save(updatedBook);
            logger.info("Book with id {} updated successfully.", id);
        } catch (Exception e) {
            logger.error("An error occurred while updating book with id {}: {}", id, e.getMessage());
            throw e;
        }
    }

    public void deleteBook(Long id) {
        try {
            logger.info("Deleting book with id {}.", id);
            Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
            bookRepository.delete(existingBook);
            logger.info("Book with id {} deleted successfully.", id);
        logger.info("deleted");
        } catch (Exception e) {
            logger.error("An error occurred while deleting book with id {}: {}", id, e.getMessage());
            throw e;
        }

    }

}

