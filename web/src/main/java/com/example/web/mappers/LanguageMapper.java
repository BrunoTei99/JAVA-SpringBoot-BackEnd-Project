package com.example.web.mappers;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import com.example.web.model.Language;
import com.example.web.model.dto.LanguageDto;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LanguageMapper {
    LanguageDto languageModelToLanguageDto(Language language);

    Language languageDtoToLanguageModel(LanguageDto languageDto);
}
