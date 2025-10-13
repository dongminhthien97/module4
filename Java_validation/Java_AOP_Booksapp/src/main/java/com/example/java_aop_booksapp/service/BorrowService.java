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
public class BorrowService implements IBorrowService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IBorrowRepository borrowRepository;

    @Override
    public String borrowBook(Long bookId) throws BookNotAvailableException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getQuantity() <= 0)
            throw new BookNotAvailableException("Sách hiện không còn để mượn!");

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        String code = generateUniqueBorrowCode();

        Borrow borrow = new Borrow();
        borrow.setBorrowCode(code);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDate.now());
        borrowRepository.save(borrow);

        return code;
    }

    @Override
    public void returnBook(String code) throws InvalidBorrowCodeException {
        Borrow borrow = borrowRepository.findByBorrowCode(code)
                .orElseThrow(() -> new InvalidBorrowCodeException("Mã mượn sách không hợp lệ!"));

        Book book = borrow.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        borrowRepository.delete(borrow);
    }

    @Override
    public String generateUniqueBorrowCode() {
        Random random = new Random();
        String code;
        do {
            code = String.format("%05d", random.nextInt(100000));
        } while (borrowRepository.existsByBorrowCode(code));
        return code;
    }
}
