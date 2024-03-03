package com.example.web.service;


import com.example.web.model.Book;
import com.example.web.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBooks(){

        List<Book> books = bookRepository.findAll();

        return books;
    }

}
