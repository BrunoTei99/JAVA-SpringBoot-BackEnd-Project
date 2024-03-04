package com.example.web.repository;

import com.example.web.model.Book;
import com.example.web.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
