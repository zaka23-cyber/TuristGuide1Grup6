package com.example.touristattraction.service;

import com.example.touristattraction.model.TouristAttraction;
import com.example.touristattraction.repository.TouristRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class TouristService {

    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAttractions();
    }

    public TouristAttraction getAttraction(UUID id) {
        return touristRepository.getAttraction(id);
    }

    public void addAttraction(TouristAttraction attraction) {
        touristRepository.addAttraction(attraction);
    }

    public void updateAttraction(TouristAttraction attraction) {
        touristRepository.updateAttraction(attraction);
    }

    public void deleteAttraction(UUID id) {
        touristRepository.deleteAttraction(id);
    }
}
