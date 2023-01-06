package com.example.testtask.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    String title = "Название не установлено или не найдено";
    String authors = "Авторы не найдены";
    String description = "Описание не найдено или не установлено";
    String ISBN = "ISBN не найдено или не установлено";
    String publishers = "Издательство и год не найдены или не установлены";

    // Add description, year, isbn, image, page amount, genre, tags

}
