package com.example.validation.dto;

import jakarta.validation.constraints.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
