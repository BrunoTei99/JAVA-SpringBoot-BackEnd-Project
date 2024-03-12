package com.example.web.controller;


import com.example.web.model.dto.LanguageDto;
import com.example.web.service.LanguageService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService){
        this.languageService = languageService;
    }


    @GetMapping
    public ResponseEntity<List<LanguageDto>> getAllLanguages(){
        List<LanguageDto> languagesDto = languageService.getAllLanguages();
        if ( languagesDto.isEmpty()
        ){
            return ResponseEntity.notFound().build();        }
        else{
            return  ResponseEntity.ok(languagesDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDto> getLanguageById(@PathVariable  Long id){
        LanguageDto languageDto = languageService.getLanguageById(id);
        if (languageDto != null ){
            return ResponseEntity.ok(languageDto);
        }else {
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> updateLanguage( @RequestBody LanguageDto LanguageDto){
       languageService.addNewLanguage(LanguageDto);
       return ResponseEntity.status(HttpStatus.CREATED).body("Language added successfully");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLanguageDto(@RequestBody LanguageDto updatedLangaugeDto, @PathVariable Long id){
        try {
            languageService.updateLanguage(id, updatedLangaugeDto);
            return ResponseEntity.ok("Book Updated Successfully");
        }
        catch (IllegalArgumentException e)
        {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLanguageById(@PathVariable Long id){
        try {
          languageService.deleteLanguage(id);
            return ResponseEntity.ok("Book Deleted Successfuly");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
