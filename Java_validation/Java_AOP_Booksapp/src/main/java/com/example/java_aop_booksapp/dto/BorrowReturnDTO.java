package com.example.java_aop_booksapp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowReturnDTO {

    @NotBlank(message = "Mã mượn sách không được để trống")
    @Pattern(regexp = "\\d{5}", message = "Mã mượn sách phải gồm 5 chữ số")
    private String borrowCode;

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
    }

}
