package com.example.demo.controller;

import com.example.demo.dao.MySQLDAO;
import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate; // Додано!
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private MySQLDAO bookService;

    @GetMapping
    public String viewBooks(Model model) {
        List<Book> books = bookService.displayAllBooks();
        model.addAttribute("books", books);
        return "books"; // Назва HTML-файлу
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String title,
                          @RequestParam int publisherId,
                          @RequestParam int authorId,
                          @RequestParam int genreId,
                          @RequestParam String releaseDate,
                          Model model) {
        bookService.addBook(new Book(0, title, publisherId, authorId, genreId, LocalDate.parse(releaseDate)));
        return "redirect:/books";
    }

    @PostMapping("/update")
    public String updateBook(@RequestParam int id,
                             @RequestParam(required = false) String title,
                             @RequestParam(required = false) Integer publisherId,
                             @RequestParam(required = false) Integer authorId,
                             @RequestParam(required = false) Integer genreId,
                             @RequestParam(required = false) String releaseDate,
                             Model model) {
        bookService.updateBook(new Book(id, title, publisherId, authorId, genreId,
                releaseDate != null ? LocalDate.parse(releaseDate) : null));
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam int id, Model model) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/search")
    public String searchBook(@RequestParam String title, Model model) {
        List<Book> books = bookService.searchBooks(title);
        model.addAttribute("books", books);
        return "books";
    }
}
