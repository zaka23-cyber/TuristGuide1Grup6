package com.example.touristattraction.model;

import java.util.List;
import java.util.UUID;

public class TouristAttraction {
    private UUID id;
    private String name;
    private String description;
    private String city;
    private List<String> tags;
    private String imagePath;

    public TouristAttraction() {
        this.id = UUID.randomUUID();
    }

    public TouristAttraction(String name, String description, String city, List<String> tags) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCity() { return city; }
    public List<String> getTags() { return tags; }
    public String getImagePath() { return imagePath; }

    public void setId(UUID id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCity(String city) { this.city = city; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
