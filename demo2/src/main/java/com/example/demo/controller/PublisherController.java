package com.example.demo.controller;

import com.example.demo.dao.MySQLDAO;
import com.example.demo.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private MySQLDAO publisherService;

    // Показати усіх видавців
    @GetMapping
    public String viewPublishers(Model model) {
        List<Publisher> publishers = publisherService.displayAllPublishers();
        model.addAttribute("publishers", publishers);
        return "publishers"; // Назва HTML-файлу
    }

    // Додати видавця
    @PostMapping("/add")
    public String addPublisher(@RequestParam String name,
                               @RequestParam String location,
                               Model model) {
        Publisher publisher = new Publisher(0, name, location);
        publisherService.addPublisher(publisher);
        return "redirect:/publishers";
    }

    // Оновити видавця
    @PostMapping("/update")
    public String updatePublisher(@RequestParam int id,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String location,
                                  Model model) {
        Publisher publisher = new Publisher(id, name, location);
        publisherService.updatePublisher(publisher);
        return "redirect:/publishers";
    }

    // Видалити видавця
    @PostMapping("/delete")
    public String deletePublisher(@RequestParam int id) {
        publisherService.deletePublisher(id);
        return "redirect:/publishers";
    }

    // Пошук видавців
    @PostMapping("/search")
    public String searchPublisher(@RequestParam String name, Model model) {
        List<Publisher> publishers = publisherService.searchPublishers(name);
        model.addAttribute("publishers", publishers);
        return "publishers";
    }
}
