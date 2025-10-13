package com.example.java_aop_booksapp.repository;


import com.example.java_aop_booksapp.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBorrowRepository extends JpaRepository<Borrow, String> {
    Optional<Borrow> findByBorrowCode(String borrowCode);
    boolean existsByBorrowCode(String borrowCode);
}
