package com.example.web.service;

import com.example.web.mappers.BookMapper;
import com.example.web.mappers.LanguageMapper;
import com.example.web.model.Book;
import com.example.web.model.Language;
import com.example.web.model.dto.LanguageDto;
import com.example.web.repository.LanguageRepository;
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

    private static final Logger logger = LoggerFactory.getLogger(LanguageService.class);

    @Autowired
    public LanguageService(LanguageRepository languageRepository, LanguageMapper languageMapper){
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }


    public List<LanguageDto> getAllLanguages(){

        List<LanguageDto> languagesDto = languageMapper.languagesToLanguageDto(languageRepository.findAll());
        return  languagesDto;
    }

    public LanguageDto getLanguageById(Long id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);

        if(optionalLanguage.isPresent()){
            return languageMapper.languageToLanguageDto(optionalLanguage.get());
        }
        else {
            return null;
        }
    }

    public void addNewLanguage(LanguageDto languageDto){
        languageRepository.save(languageMapper.languageDtoToLanguage(languageDto));
    }

    public void updateLanguage(Long id, LanguageDto updatedLanguageDto){
        Language existingLanguage = languageRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Language Not Found "));
        Language updatedLanguage = languageMapper.languageDtoToLanguage(updatedLanguageDto);
        updatedLanguage.setId(existingLanguage.getId());
        languageRepository.save(updatedLanguage);
    }

    public void deleteLanguage(Long id){
        Language existingLanguage = languageRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Language Not Found "));
        languageRepository.deleteById(id);
    }




}
