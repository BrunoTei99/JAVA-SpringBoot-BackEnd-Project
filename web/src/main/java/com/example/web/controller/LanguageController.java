package com.example.web.controller;

import com.example.web.model.dto.LanguageDto;
import com.example.web.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/language")
public class LanguageController {

    private final LanguageService languageService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageController.class);

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public ResponseEntity<List<LanguageDto>> getAllLanguages() {
        LOGGER.info("GET request received to fetch all languages");
        List<LanguageDto> languagesDto = languageService.getAllLanguages();
        if (languagesDto.isEmpty()) {
            LOGGER.warn("No languages found");
            return ResponseEntity.notFound().build();
        } else {
            LOGGER.info("Returning {} languages", languagesDto.size());
            return ResponseEntity.ok(languagesDto);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<LanguageDto> getLanguageById(@PathVariable Long id) {
        LOGGER.info("GET request received to fetch language with id {}", id);
        LanguageDto languageDto = languageService.getLanguageById(id);
        LOGGER.info("Returning language with id {}: {}", id, languageDto);
        return ResponseEntity.ok(languageDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateLanguage(@RequestBody LanguageDto updateBookDto, @PathVariable Long id) {
        LOGGER.info("PUT request received to update language with id {}", id);
        try {
            languageService.updateLanguage(id, updateBookDto);
            LOGGER.info("Language with id {} updated successfully", id);
            return ResponseEntity.ok("Language updated successfully");
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to update language with id {}: {}", id, e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addNewLanguage(@RequestBody LanguageDto languageDto) {
        LOGGER.info("POST request received to add a new language: {}", languageDto);
        languageService.addNewLanguage(languageDto);
        LOGGER.info("New language added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body("Language added successfully");
    }

    //    @DeleteMapping("{id}")
    //    public ResponseEntity<String> deleteLanguageById(@PathVariable Long id) {
    //        LOGGER.info("DELETE request received to delete language with id {}", id);
    //        try {
    //            languageService.deleteLanguage(id);
    //            LOGGER.info("Language with id {} deleted successfully", id);
    //            return ResponseEntity.ok("Language deleted successfully");
    //        } catch (IllegalArgumentException e) {
    //            LOGGER.error("Failed to delete language with id {}: {}", id, e.getMessage());
    //            return ResponseEntity.badRequest().body(e.getMessage());
    //        }
    //    }
}
