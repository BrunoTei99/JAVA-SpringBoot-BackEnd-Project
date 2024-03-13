package com.example.web.service;

import com.example.web.mappers.AuthorMapper;
import com.example.web.model.Author;
import com.example.web.model.dto.AuthorDto;
import com.example.web.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    private  final AuthorMapper authorMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageService.class);


    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper){
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }


    public List<AuthorDto> getAllAuthors(){
        LOGGER.info("Fetching all authors from the database");

        List<AuthorDto> authorsDto = authorMapper.authorsModelToAuthorsDto(authorRepository.findAll());
        LOGGER.info("Retrieved {} authors", authorsDto.size());
        return authorsDto;
    }

    public AuthorDto getAuthorById(Long id) {
        LOGGER.info("Fetching Author with ID {}", id);
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            LOGGER.info("Author with ID {} found", id);
            return authorMapper.authorModelToAuthorDto(optionalAuthor.get());
        } else {
            LOGGER.warn("Author with ID {} not found", id);
            return null;
        }
    }

    public void addNewAuthor(AuthorDto authorDto){
        LOGGER.info("Adding a new author: {}", authorDto);
        Author author = authorMapper.authorDtoToAuthorModel(authorDto);
        authorRepository.save(author);
        LOGGER.info("New author added successfully");
    }

    public void updateAuthor(Long id, AuthorDto updatedAuthorDto){
        LOGGER.info("Updating author with ID {}", id);
        Author existingAuthor = authorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Author updatedAuthor = authorMapper.authorDtoToAuthorModel(updatedAuthorDto);
        updatedAuthor.setId(existingAuthor.getId());
        authorRepository.save(updatedAuthor);
        LOGGER.info("Author with ID {} updated successfully", id);

    }

}
