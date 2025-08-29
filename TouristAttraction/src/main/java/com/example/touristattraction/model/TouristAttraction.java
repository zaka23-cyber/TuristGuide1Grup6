package com.example.touristattraction.model;

import java.util.ArrayList;

public class TouristAttraction {
    private String name;
    private String description;

    public TouristAttraction(String name, String description){
        this.description = description;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
