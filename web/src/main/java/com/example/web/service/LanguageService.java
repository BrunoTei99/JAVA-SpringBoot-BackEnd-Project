package com.example.web.service;


import com.example.web.mappers.BookMapper;
import com.example.web.mappers.LanguageMapper;
import com.example.web.model.Language;
import com.example.web.model.dto.LanguageDto;
import com.example.web.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    private final LanguageMapper languageMapper;


    @Autowired
    public LanguageService(LanguageRepository languageRepository, LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

    public void addNewLanguage(LanguageDto languageDto) {
        Language language = languageMapper.languageDtoToLanguageModel(languageDto);
        languageRepository.save(language);
    }


}
