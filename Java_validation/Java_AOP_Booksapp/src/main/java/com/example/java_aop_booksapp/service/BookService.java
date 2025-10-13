package com.example.java_aop_booksapp.service;

import com.example.java_aop_booksapp.entity.Book;
import com.example.java_aop_booksapp.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sách với id = " + id));
    }

    @Override
    public void create(Book book) {
        book.setId(null);
        bookRepository.save(book);
    }

    @Override
    public void update(Book book) {
        if (!bookRepository.existsById(book.getId())) {
            throw new RuntimeException("Không tìm thấy sách để cập nhật");
        }
        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy sách để xóa");
        }
        bookRepository.deleteById(id);
    }

}
