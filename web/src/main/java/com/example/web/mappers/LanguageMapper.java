package com.example.web.mappers;

import com.example.web.model.Language;
import com.example.web.model.dto.LanguageDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface LanguageMapper {

    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    LanguageDto languageToLanguageDto(Language language);

    Language languageDtoToLanguage(LanguageDto languageDto);
}
