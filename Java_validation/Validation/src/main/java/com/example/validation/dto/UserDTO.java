package com.example.validation.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    private Long id;

    @NotBlank(message = "Firstname không được để trống")
    @Size(min = 5, max = 45, message = "Firstname phải từ 5-45 ký tự")
    private String firstName;

    @NotBlank(message = "Lastname không được để trống")
    @Size(min = 5, max = 45, message = "Lastname phải từ 5-45 ký tự")
    private String lastName;

    @Pattern(regexp = "^0[0-9]{9,10}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    @Min(value = 18, message = "Tuổi phải >= 18")
    private int age;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;
}
