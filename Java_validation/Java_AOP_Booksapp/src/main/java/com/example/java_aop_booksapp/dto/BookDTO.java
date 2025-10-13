package com.example.java_aop_booksapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

    private Long id;

    @NotBlank(message = "Tên sách không được để trống")
    private String title;

    @NotBlank(message = "Tên tác giả không được để trống")
    private String author;

    @Min(value = 1, message = "Số lượng phải >= 1")
    private int quantity;
}
