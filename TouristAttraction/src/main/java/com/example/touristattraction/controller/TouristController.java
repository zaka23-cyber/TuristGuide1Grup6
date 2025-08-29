package com.example.touristattraction.controller;

import com.example.touristattraction.model.TouristAttraction;
import com.example.touristattraction.service.TouristService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("attractions")
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    // GET alle
    @GetMapping
    public ResponseEntity<List<TouristAttraction>> getAllAttractions() {
        return ResponseEntity.ok(touristService.getAllAttractions());
    }

    // GET én attraktion via navn
    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttraction(@PathVariable String name) {
        TouristAttraction attraction = touristService.getAttraction(name);
        if (attraction != null) {
            return ResponseEntity.ok(attraction);
        }
        return ResponseEntity.notFound().build();
    }

    // POST add
    @PostMapping("/add")
    public ResponseEntity<String> addAttraction(@RequestBody TouristAttraction attraction) {
        touristService.addAttraction(attraction);
        return ResponseEntity.ok("Attraction added!");
    }

    // POST update
    @PostMapping("/update")
    public ResponseEntity<String> updateAttraction(@RequestBody TouristAttraction updated) {
        touristService.updateAttraction(updated);
        return ResponseEntity.ok("Attraction updated!");
    }

    // POST delete via navn
    @PostMapping("/delete/{name}")
    public ResponseEntity<String> deleteAttraction(@PathVariable String name) {
        touristService.deleteAttraction(name);
        return ResponseEntity.ok("Attraction deleted!");
    }
}
