//package com.example.blog_spring_boot.dto;
//
//import com.example.blog_spring_boot.entity.Blog;
//
//public record BlogDTO(Long id, String title, String author, String content, String categoryName) {
//    public static BlogDTO from(Blog blog) {
//        return new BlogDTO(
//                blog.getId(),
//                blog.getTitle(),
//                blog.getAuthor(),
//                blog.getContent(),
//                blog.getCategory() != null ? blog.getCategory().getName() : null
//        );
//    }
//}
//
