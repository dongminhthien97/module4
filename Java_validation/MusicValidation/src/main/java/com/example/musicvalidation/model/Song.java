package com.example.musicvalidation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "songs")
@Getter
@Setter
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên bài hát không được để trống")
    @Size(max = 800, message = "Tên bài hát không vượt quá 800 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9\\sÀ-ỹ]+$", message = "Tên bài hát không được chứa ký tự đặc biệt")
    private String name;

    @NotBlank(message = "Nghệ sĩ không được để trống")
    @Size(max = 300, message = "Tên nghệ sĩ không vượt quá 300 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9\\sÀ-ỹ]+$", message = "Tên nghệ sĩ không được chứa ký tự đặc biệt")
    private String artist;

    @NotBlank(message = "Thể loại không được để trống")
    @Size(max = 1000, message = "Thể loại không vượt quá 1000 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9\\sÀ-ỹ,]+$", message = "Thể loại chỉ được chứa dấu phẩy, không có ký tự đặc biệt khác")
    private String genre;
    @Column(name = "file_path")
    private String filePath;
}
