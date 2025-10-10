package com.example.musicvalidation.repository;

import com.example.musicvalidation.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongRepository{
    private final ISongRepository songRepository;
    public SongRepository(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    public List<Song> searchByNameOrGenre(String keyword){
        return songRepository.searchByNameOrGenre(keyword, null).getContent();
    }
    public Song save(Song song){
        return songRepository.save(song);
    }
    public Song findById(Long id){
        return songRepository.findById(id).orElse(null);
    }

    public List<Song> findAll(){
        return songRepository.findAll();
    }
}
