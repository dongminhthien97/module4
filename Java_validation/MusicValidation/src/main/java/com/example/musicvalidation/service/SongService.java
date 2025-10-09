package com.example.musicvalidation.service;

import com.example.musicvalidation.model.Song;
import com.example.musicvalidation.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements ISongService {
    @Autowired
    private ISongRepository songRepository;

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Song song) {
        songRepository.save(song);
    }


    @Override
    public Song update(Long id, Song newSong) {
        Song existing = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài hát!"));

        existing.setName(newSong.getName());
        existing.setArtist(newSong.getArtist());
        existing.setGenre(newSong.getGenre());

        return songRepository.save(existing);
    }
}
