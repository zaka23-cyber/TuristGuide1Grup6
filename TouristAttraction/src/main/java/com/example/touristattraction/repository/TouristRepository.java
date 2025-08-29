package com.example.touristattraction.repository;

import com.example.touristattraction.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {

    private List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository() {
        attractions.add(new TouristAttraction("Den Lille Havfrue", "Københavns mest berømte statue"));
        attractions.add(new TouristAttraction("Tivoli", "En af verdens ældste forlystelsesparker"));
    }

    // CREATE
    public void addAttraction(TouristAttraction attraction) {
        attractions.add(attraction);
    }

    // READ alle
    public List<TouristAttraction> getAttractions() {
        return attractions;
    }

    // READ én via navn
    public TouristAttraction getAttraction(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }

    // UPDATE via navn
    public void updateAttraction(TouristAttraction updatedAttraction) {
        for (int i = 0; i < attractions.size(); i++) {
            if (attractions.get(i).getName().equalsIgnoreCase(updatedAttraction.getName())) {
                attractions.set(i, updatedAttraction);
                return;
            }
        }
    }

    // DELETE via navn
    public void deleteAttraction(String name) {
        attractions.removeIf(a -> a.getName().equalsIgnoreCase(name));
    }
}
