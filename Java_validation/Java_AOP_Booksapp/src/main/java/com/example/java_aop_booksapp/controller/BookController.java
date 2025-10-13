package com.example.java_aop_booksapp.controller;

import com.example.java_aop_booksapp.dto.BookDTO;
import com.example.java_aop_booksapp.entity.Book;
import com.example.java_aop_booksapp.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book/books";
    }

    @GetMapping("/{id}")
    public String showBookDetail(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book/book-detail";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("bookDTO", new BookDTO());
        return "book/book-create";
    }

    @PostMapping("/create")
    public String saveBook(@Valid @ModelAttribute("bookDTO") BookDTO bookDTO,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/book-create";
        }

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setQuantity(bookDTO.getQuantity());

        if (bookDTO.getId() == null) {
            bookService.create(book);
        } else {
            bookService.update(book);
        }

        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);

        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setQuantity(book.getQuantity());

        model.addAttribute("bookDTO", dto);
        return "book/book-create";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
