package com.example.musicvalidation.service;

import com.example.musicvalidation.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    Song findById(Long id);
    void save(Song song);
    Song update(Long id, Song song);
    Page<Song> search(String keyword, Pageable pageable);
    Page<Song> findAll(Pageable pageable);

}
