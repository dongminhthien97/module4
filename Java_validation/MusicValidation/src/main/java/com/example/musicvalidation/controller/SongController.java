package com.example.musicvalidation.controller;

import com.example.musicvalidation.model.Song;
import com.example.musicvalidation.repository.SongRepository;
import com.example.musicvalidation.service.FileStorageService;
import com.example.musicvalidation.service.ISongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private ISongService songService;
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public String listSongs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword,
            Model model) {

        Page<Song> songPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            songPage = songService.search(keyword, PageRequest.of(page, size));
            model.addAttribute("keyword", keyword);
        } else {
            songPage = songService.findAll(PageRequest.of(page, size));
        }

        model.addAttribute("songs", songPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", songPage.getTotalPages());
        model.addAttribute("totalItems", songPage.getTotalElements());
        model.addAttribute("size", size);

        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("song", new Song());
        return "create";
    }

    @PostMapping("/save")
    public String saveSong(@Valid @ModelAttribute("song") Song song,
                           BindingResult result,
                           @RequestParam("file") MultipartFile file,
                           RedirectAttributes redirect) throws IOException {
        if (result.hasErrors()) {
            return "create";
        }

        String fileName = fileStorageService.saveFile(file);
        song.setFilePath(fileName);

        songService.save(song);
        redirect.addFlashAttribute("message", "Upload thành công!");
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
                             @RequestParam("file") MultipartFile file,
                             RedirectAttributes redirect) throws IOException {
        if (result.hasErrors()) {
            return "songs/edit";
        }

        if (!file.isEmpty()) {
            String fileName = fileStorageService.saveFile(file);
            song.setFilePath(fileName);
        }

        songRepository.save(song);
        redirect.addFlashAttribute("message", "Cập nhật bài hát thành công!");
        return "redirect:/songs";
    }
}

