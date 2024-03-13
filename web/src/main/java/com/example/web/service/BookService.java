package com.example.web.service;

import com.example.web.mappers.BookMapper;
import com.example.web.model.Book;
import com.example.web.model.dto.BookDto;
import com.example.web.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        logger.info("Fetching all books.");
        List<BookDto> booksDto = bookMapper.booksModelToBooksDto(bookRepository.findAll());
        logger.info("Fetched {} books.", booksDto.size());
        return booksDto;
    }

    public BookDto getBookById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid book id: " + id);
        }
        logger.info("Fetching book with id {}.", id);
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            logger.info("Book with id {} found.", id);
            return bookMapper.bookModelToBookDto(optionalBook.get());
        } else {
            logger.warn("Book with id {} not found.", id);
            return null;
        }
    }

    public void addNewBook(BookDto bookDto) {
        logger.info("Adding a new book.");
        Book book = bookMapper.bookDtoToBookModel(bookDto);
        bookRepository.save(book);
        logger.info("Book added successfully: {}.", book);
    }

    @Transactional
    public void updateBook(Long id, BookDto updatedBookDTO) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid book id: " + id);
        }
        logger.info("Updating book with id {}.", id);
        Book existingBook = bookRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        Book updatedBook = bookMapper.bookDtoToBookModel(updatedBookDTO);
        updatedBook.setId(existingBook.getId());
        bookRepository.save(updatedBook);
        logger.info("Book with id {} updated successfully.", id);
    }

    @Transactional
    public void deleteBook(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid book id: " + id);
        }
        logger.info("Deleting book with id {}.", id);
        Book existingBook = bookRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        bookRepository.delete(existingBook);
        logger.info("Book with id {} deleted successfully.", id);
    }
}
