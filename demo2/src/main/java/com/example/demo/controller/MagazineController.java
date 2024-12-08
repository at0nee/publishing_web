package com.example.demo.controller;

import com.example.demo.dao.MySQLDAO;
import com.example.demo.model.Magazine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/magazines")
public class MagazineController {

    @Autowired
    private MySQLDAO magazineService;

    @GetMapping
    public String viewMagazines(Model model) {
        List<Magazine> magazines = magazineService.displayAllMagazines();
        model.addAttribute("magazines", magazines);
        return "magazines"; // Назва HTML-файлу
    }

    @PostMapping("/add")
    public String addMagazine(@RequestParam String title,
                              @RequestParam int publisherId,
                              @RequestParam String releaseDate,
                              Model model) {
        Magazine magazine = new Magazine(0, title, publisherId, LocalDate.parse(releaseDate));
        magazineService.addMagazine(magazine);
        return "redirect:/magazines";
    }


    @PostMapping("/update")
    public String updateMagazine(@RequestParam int id,
                                 @RequestParam(required = false) String title,
                                 @RequestParam(required = false) Integer publisherId,
                                 @RequestParam(required = false) String releaseDate,
                                 Model model) {
        Magazine magazine = new Magazine(id, title, publisherId,
                releaseDate != null ? LocalDate.parse(releaseDate) : null);
        magazineService.updateMagazine(magazine);
        return "redirect:/magazines";
    }


    @PostMapping("/delete")
    public String deleteMagazine(@RequestParam int id) {
        magazineService.deleteMagazine(id);
        return "redirect:/magazines";
    }

    @PostMapping("/search")
    public String searchMagazine(@RequestParam String title, Model model) {
        List<Magazine> magazines = magazineService.searchMagazines(title);
        model.addAttribute("magazines", magazines);
        return "magazines";
    }
}
