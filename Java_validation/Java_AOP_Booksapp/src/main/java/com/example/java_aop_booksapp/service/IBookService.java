package com.example.java_aop_booksapp.service;

import com.example.java_aop_booksapp.entity.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    Book findById(Long id);
    void update(Book book);
    void create(Book book);
    void delete(Long id);
}
