package com.example.web.controller;


import com.example.web.model.dto.LanguageDto;
import com.example.web.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewLanguage(@RequestBody LanguageDto languageDto) {
        languageService.addNewLanguage(languageDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Language added successfully");
    }
}
