package com.example.touristattraction.repository;

import com.example.touristattraction.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TouristRepository {

    private final Map<UUID, TouristAttraction> attractions = new LinkedHashMap<>();

    public TouristRepository() {
        TouristAttraction a1 = new TouristAttraction(
                "Den Lille Havfrue",
                "En berømt statue i København",
                "København",
                Arrays.asList("historie", "kultur", "kunst")
        );
        TouristAttraction a2 = new TouristAttraction(
                "Tivoli",
                "Forlystelsespark midt i København",
                "København",
                Arrays.asList("sjov", "familie", "forlystelser")
        );
        attractions.put(a1.getId(), a1);
        attractions.put(a2.getId(), a2);
    }

    public List<TouristAttraction> getAttractions() {
        return new ArrayList<>(attractions.values());
    }

    public TouristAttraction getAttraction(UUID id) {
        return attractions.get(id);
    }

    public void addAttraction(TouristAttraction attraction) {
        attractions.put(attraction.getId(), attraction);
    }

    public void updateAttraction(TouristAttraction updatedAttraction) {
        attractions.put(updatedAttraction.getId(), updatedAttraction);
    }

    public void deleteAttraction(UUID id) {
        attractions.remove(id);
    }
}
