package com.example.testtask.controller;

import com.example.testtask.model.Book;
import com.example.testtask.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books/{amount}")
    public List<Book>getAllBooks(@PathVariable String amount){
      return bookService.getAllBooksFromLabirint(amount);
    }
}
