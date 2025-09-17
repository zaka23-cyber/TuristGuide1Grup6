package com.example.touristattraction.controller;

import com.example.touristattraction.model.TouristAttraction;
import com.example.touristattraction.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class TouristViewController {

    private final TouristService touristService;

    public TouristViewController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("/attractions")
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";
    }

    @GetMapping("/attractions/{id}")
    public String getAttraction(@PathVariable UUID id, Model model) {
        TouristAttraction attraction = touristService.getAttraction(id);
        model.addAttribute("attraction", attraction);
        return "singleAttraction";
    }

    @GetMapping("/attractions/new")
    public String newAttractionForm(Model model) {
        model.addAttribute("attraction", new TouristAttraction());
        return "newAttraction";
    }

    @PostMapping("/attractions")
    public String createAttraction(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String city,
            @RequestParam String tags) {

        List<String> tagList = Arrays.asList(tags.split(",\\s*"));
        TouristAttraction attraction =
                new TouristAttraction(name, description, city, tagList);

        touristService.addAttraction(attraction);
        return "redirect:/attractions";
    }

    @GetMapping("/attractions/{id}/edit")
    public String editAttractionForm(@PathVariable UUID id, Model model) {
        TouristAttraction attraction = touristService.getAttraction(id);
        model.addAttribute("attraction", attraction);
        return "editAttraction";
    }

    @PostMapping("/attractions/{id}/edit")
    public String updateAttraction(
            @PathVariable UUID id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String city,
            @RequestParam String tags) {

        List<String> tagList = Arrays.asList(tags.split(",\\s*"));
        TouristAttraction attraction =
                new TouristAttraction(name, description, city, tagList);

        try {
            var field = TouristAttraction.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(attraction, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        touristService.updateAttraction(attraction);
        return "redirect:/attractions/" + id;
    }

    @PostMapping("/attractions/{id}/delete")
    public String deleteAttraction(@PathVariable UUID id) {
        touristService.deleteAttraction(id);
        return "redirect:/attractions";
    }
}
