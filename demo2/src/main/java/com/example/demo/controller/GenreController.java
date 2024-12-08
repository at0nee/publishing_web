package com.example.demo.controller;

import com.example.demo.dao.MySQLDAO;
import com.example.demo.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private MySQLDAO genreService; // Сервіс для роботи з DAO

    @GetMapping
    public String manageGenres(Model model) {
        List<Genre> genres = genreService.displayAllGenres();
        model.addAttribute("genres", genres);
        return "genres"; // Назва HTML-сторінки для відображення
    }

    // Додавання жанру
    @PostMapping("/add")
    public String addGenre(@RequestParam String name, Model model) {
        genreService.addGenre(name);
        model.addAttribute("message", "Жанр успішно додано!");
        return "redirect:/genres"; // Перенаправлення на головну сторінку жанрів
    }

    // Оновлення жанру
    @PostMapping("/update")
    public String updateGenre(@RequestParam int id, @RequestParam String name, Model model) {
        genreService.updateGenre(id, name);
        model.addAttribute("message", "Жанр успішно оновлено!");
        return "redirect:/genres"; // Перенаправлення на головну сторінку жанрів
    }

    // Видалення жанру
    @PostMapping("/delete")
    public String deleteGenre(@RequestParam int id, Model model) {
        genreService.deleteGenre(id);
        model.addAttribute("message", "Жанр успішно видалено!");
        return "redirect:/genres"; // Перенаправлення на головну сторінку жанрів
    }

    // Пошук жанрів
    @PostMapping("/search")
    public String searchGenre(@RequestParam String name, Model model) {
        List<Genre> genres = genreService.searchGenres(name);
        model.addAttribute("genres", genres);
        return "genres"; // Назва HTML-сторінки для відображення
    }
}
