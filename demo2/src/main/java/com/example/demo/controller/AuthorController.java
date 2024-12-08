package com.example.demo.controller;

import com.example.demo.dao.MySQLDAO;
import com.example.demo.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private MySQLDAO mySQLDAO; // DAO для роботи з авторами

    // Відображення всіх авторів
    @GetMapping
    public String manageAuthors(Model model) {
        List<Author> authors = mySQLDAO.displayAllAuthors();
        model.addAttribute("authors", authors);
        return "authors"; // Назва HTML-шаблону
    }

    // Додавання автора
    @PostMapping("/add")
    public String addAuthor(@RequestParam String name, @RequestParam int birthYear, Model model) {
        mySQLDAO.addAuthor(name, birthYear);
        model.addAttribute("message", "Автор успішно доданий!");
        return "redirect:/authors"; // Перенаправлення на сторінку авторів
    }

    // Оновлення автора
    @PostMapping("/update")
    public String updateAuthor(@RequestParam int id, @RequestParam String name, @RequestParam int birthYear, Model model) {
        mySQLDAO.updateAuthor(id, name, birthYear);
        model.addAttribute("message", "Автор успішно оновлений!");
        return "redirect:/authors"; // Перенаправлення на сторінку авторів
    }

    // Видалення автора
    @PostMapping("/delete")
    public String deleteAuthor(@RequestParam int id, Model model) {
        mySQLDAO.deleteAuthor(id);
        model.addAttribute("message", "Автор успішно видалений!");
        return "redirect:/authors"; // Перенаправлення на сторінку авторів
    }

    // Пошук авторів
    @PostMapping("/search")
    public String searchAuthors(@RequestParam String name, Model model) {
        List<Author> authors = mySQLDAO.searchAuthors(name);
        model.addAttribute("authors", authors);
        return "authors"; // Назва HTML-шаблону
    }
}
