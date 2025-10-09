package com.example.musicvalidation.service;

import com.example.musicvalidation.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    Song findById(Long id);
    void save(Song song);
    Song update(Long id, Song song);

}
