package com.example.java_aop_booksapp.controller;

import com.example.java_aop_booksapp.dto.BorrowRequestDTO;
import com.example.java_aop_booksapp.dto.BorrowReturnDTO;
import com.example.java_aop_booksapp.exception.BookNotAvailableException;
import com.example.java_aop_booksapp.exception.InvalidBorrowCodeException;
import com.example.java_aop_booksapp.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id, Model model) {
        try {
            String code = borrowService.borrowBook(id);
            model.addAttribute("code", code);
            return "borrow/borrow-success";
        } catch (BookNotAvailableException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error/error";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình mượn sách.");
            return "error/error";
        }
    }


    @PostMapping("/borrow")
    public String borrowBookManual(@Valid @ModelAttribute("borrowRequestDTO") BorrowRequestDTO dto,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            return "borrow/borrow-form";
        }
        try {
            String code = borrowService.borrowBook(dto.getBookId());
            model.addAttribute("code", code);
            return "borrow/borrow-success";
        } catch (BookNotAvailableException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error/error";
        }
    }

    @GetMapping("/return")
    public String showReturnForm(Model model) {
        model.addAttribute("borrowReturnDTO", new BorrowReturnDTO());
        return "return/return"; // return.html
    }

    @PostMapping("/return")
    public String returnBook(
            @Valid @ModelAttribute("borrowReturnDTO") BorrowReturnDTO borrowReturnDTO,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "return/return";
        }

        try {
            borrowService.returnBook(borrowReturnDTO.getBorrowCode());
            model.addAttribute("message", "✅ Trả sách thành công!");
            return "return/return-success";
        } catch (InvalidBorrowCodeException e) {
            model.addAttribute("error", e.getMessage());
            return "return/return";
        }
    }
}
