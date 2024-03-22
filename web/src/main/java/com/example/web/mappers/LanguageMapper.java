package com.example.web.mappers;

import com.example.web.model.Language;
import com.example.web.model.dto.LanguageDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LanguageMapper {

    LanguageDto languageToLanguageDto(Language language);

    Language languageDtoToLanguage(LanguageDto languageDto);
}
