package com.example.web.repository;

import com.example.web.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query(
            value = "SELECT * FROM Language l WHERE l.id = 1",
            nativeQuery = true)
    Collection<Language> findLanguageWithId1();
}