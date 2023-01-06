package com.example.testtask.service;

import com.example.testtask.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public void save(Book book);
    public List<Book> getAllBooksFromLabirint(String amount);

}
