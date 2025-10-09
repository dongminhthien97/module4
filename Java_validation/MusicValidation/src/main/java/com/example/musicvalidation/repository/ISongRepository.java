package com.example.musicvalidation.repository;

import com.example.musicvalidation.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISongRepository extends JpaRepository<Song, Long> {
    @Query("SELECT s FROM Song s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.genre) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Song> searchByNameOrGenre(@Param("keyword") String keyword);
}
