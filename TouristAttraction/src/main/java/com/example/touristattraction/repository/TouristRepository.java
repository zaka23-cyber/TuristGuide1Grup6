package com.example.touristattraction.repository;

import com.example.touristattraction.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TouristRepository {

    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<TouristAttraction> attractionRowMapper = new RowMapper<>() {
        @Override
        public TouristAttraction mapRow(ResultSet rs, int rowNum) throws SQLException {
            TouristAttraction a = new TouristAttraction();
            String idStr = rs.getString("id");
            if (idStr != null) {
                try {
                    a.setId(UUID.fromString(idStr));
                } catch (IllegalArgumentException e) {
                    // hvis id ikke er i UUID-format, behold som null eller log
                    a.setId(null);
                }
            }
            a.setName(rs.getString("name"));
            a.setDescription(rs.getString("description"));
            a.setCity(rs.getString("city"));

            String tagsCsv = rs.getString("tags");
            if (tagsCsv != null && !tagsCsv.isBlank()) {
                List<String> tags = Arrays.stream(tagsCsv.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
                a.setTags(tags);
            } else {
                a.setTags(new ArrayList<>());
            }

            a.setImagePath(rs.getString("image_path"));
            return a;
        }
    };

    public List<TouristAttraction> getAttractions() {
        String sql = "SELECT * FROM tourist_attraction";
        return jdbcTemplate.query(sql, attractionRowMapper);
    }

    public TouristAttraction getAttraction(UUID id) {
        String sql = "SELECT * FROM tourist_attraction WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, attractionRowMapper, id.toString());
    }

    public void addAttraction(TouristAttraction attraction) {
        String sql = "INSERT INTO tourist_attraction (id, name, description, city, tags, image_path) VALUES (?, ?, ?, ?, ?, ?)";
        String tags = (attraction.getTags() == null || attraction.getTags().isEmpty()) ? null : String.join(",", attraction.getTags());
        String idStr = (attraction.getId() == null) ? UUID.randomUUID().toString() : attraction.getId().toString();
        jdbcTemplate.update(sql, idStr, attraction.getName(), attraction.getDescription(), attraction.getCity(), tags, attraction.getImagePath());
    }

    public void updateAttraction(TouristAttraction attraction) {
        String sql = "UPDATE tourist_attraction SET name=?, description=?, city=?, tags=?, image_path=? WHERE id=?";
        String tags = (attraction.getTags() == null || attraction.getTags().isEmpty()) ? null : String.join(",", attraction.getTags());
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(), attraction.getCity(), tags, attraction.getImagePath(), attraction.getId().toString());
    }

    public void deleteAttraction(UUID id) {
        String sql = "DELETE FROM tourist_attraction WHERE id = ?";
        jdbcTemplate.update(sql, id.toString());
    }
}


