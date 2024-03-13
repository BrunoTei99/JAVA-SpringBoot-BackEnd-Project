package com.example.web.service;

import com.example.web.mappers.LanguageMapper;
import com.example.web.model.Language;
import com.example.web.model.dto.LanguageDto;
import com.example.web.repository.LanguageRepository;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageService.class);

    @Autowired
    public LanguageService(LanguageRepository languageRepository, LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

    public List<LanguageDto> getAllLanguages() {
        LOGGER.info("Fetching all languages from the database");
        try {
            List<LanguageDto> languagesDto = languageMapper.languagesModelToLangaugesDto(languageRepository.findAll());
            LOGGER.info("Retrieved {} languages", languagesDto.size());
            return languagesDto;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all languages: {}", e.getMessage());
            throw e;
        }
    }

    public LanguageDto getLanguageById(Long id) {
        LOGGER.info("Fetching language with ID {}", id);
        try {
            Optional<Language> optionalLanguage = languageRepository.findById(id);
            if (optionalLanguage.isPresent()) {
                LOGGER.info("Language with ID {} found", id);
                return languageMapper.languageModelToLanguageDto(optionalLanguage.get());
            } else {
                LOGGER.warn("Language with ID {} not found", id);
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching language with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void updateLanguage(Long id, LanguageDto updatedLanguageDto) {
        LOGGER.info("Updating language with ID {}", id);
        try {
            Language existingLanguage = languageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Language not found"));
            Language updatedLanguage = languageMapper.languageDtoToLanguageModel(updatedLanguageDto);
            updatedLanguage.setId(existingLanguage.getId());
            languageRepository.save(updatedLanguage);
            LOGGER.info("Language with ID {} updated successfully", id);
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating language with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    public void addNewLanguage(LanguageDto languageDto) {
        LOGGER.info("Adding a new language: {}", languageDto);
        try {
            Language language = languageMapper.languageDtoToLanguageModel(languageDto);
            languageRepository.save(language);
            LOGGER.info("New language added successfully");
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding a new language: {}", e.getMessage());
            throw e;
        }
    }


}
