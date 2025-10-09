package com.example.musicvalidation.controller;

import com.example.musicvalidation.model.Song;
import com.example.musicvalidation.repository.SongRepository;
import com.example.musicvalidation.service.ISongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private ISongService songService;
    @Autowired
    private SongRepository songRepository;

    @GetMapping
    public String listSongs(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Song> songs;
        if (keyword != null && !keyword.trim().isEmpty()) {
            songs = songRepository.searchByNameOrGenre(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            songs = songRepository.findAll();
        }
        model.addAttribute("songs", songs);
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("song", new Song());
        return "create";
    }

    @PostMapping("/save")
    public String saveSong(@Valid @ModelAttribute("song") Song song,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "create";
        }
        songService.save(song);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);
        return "edit";
    }

    @PostMapping("/update")
    public String updateSong(@Valid @ModelAttribute("song") Song song,
                             BindingResult result,
                             RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "edit";
        }

        songService.update(song.getId(), song);
        redirect.addFlashAttribute("message", "Cập nhật bài hát thành công!");
        return "redirect:/songs";
    }
}

