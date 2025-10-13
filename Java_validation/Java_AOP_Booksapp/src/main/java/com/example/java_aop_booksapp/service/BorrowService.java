package com.example.java_aop_booksapp.service;

import com.example.java_aop_booksapp.entity.Book;
import com.example.java_aop_booksapp.entity.Borrow;
import com.example.java_aop_booksapp.exception.BookNotAvailableException;
import com.example.java_aop_booksapp.exception.InvalidBorrowCodeException;
import com.example.java_aop_booksapp.repository.IBookRepository;
import com.example.java_aop_booksapp.repository.IBorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class BorrowService {

    @Autowired
    private IBookRepository bookRepo;

    @Autowired
    private IBorrowRepository borrowRepo;

    public String borrowBook(Long bookId) throws BookNotAvailableException {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getQuantity() <= 0)
            throw new BookNotAvailableException("Sách hiện không còn để mượn!");

        book.setQuantity(book.getQuantity() - 1);
        bookRepo.save(book);

        String code = generateUniqueBorrowCode();

        Borrow borrow = new Borrow();
        borrow.setBorrowCode(code);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDate.now());
        borrowRepo.save(borrow);

        return code;
    }

    public void returnBook(String code) throws InvalidBorrowCodeException {
        Borrow borrow = borrowRepo.findByBorrowCode(code)
                .orElseThrow(() -> new InvalidBorrowCodeException("Mã mượn sách không hợp lệ!"));

        Book book = borrow.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepo.save(book);
        borrowRepo.delete(borrow);
    }

    private String generateUniqueBorrowCode() {
        Random random = new Random();
        String code;
        do {
            code = String.format("%05d", random.nextInt(100000)); // 5 chữ số
        } while (borrowRepo.existsByBorrowCode(code)); // kiểm tra trùng
        return code;
    }
}
