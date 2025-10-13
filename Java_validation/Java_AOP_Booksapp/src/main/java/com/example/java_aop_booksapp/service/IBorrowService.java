package com.example.java_aop_booksapp.service;

public interface IBorrowService {
    String borrowBook(Long bookId);
    void returnBook(String borrowCode);
    String generateUniqueBorrowCode();
}