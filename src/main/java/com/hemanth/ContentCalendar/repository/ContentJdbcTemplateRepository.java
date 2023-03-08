package com.hemanth.ContentCalendar.repository;

import com.hemanth.ContentCalendar.entity.Content;
import com.hemanth.ContentCalendar.entity.Status;
import com.hemanth.ContentCalendar.entity.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContentJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Content mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return new Content(resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("desc"),
                Status.valueOf(resultSet.getString("status")),
                Type.valueOf(resultSet.getString("type")),
                resultSet.getTimestamp("date_created").toLocalDateTime(),
                resultSet.getTimestamp("date_updated").toLocalDateTime(),
                resultSet.getString("url")
                );
    }
    public List<Content> getAllContent(){
        String sql = "SELECT * FROM Content";
        return jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
    }
    public void createContent(String title, String desc, String status, String contentType, String URL){
        String sql = "INSERT INTO Content (title, desc, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL );
    }
    public void updateContent(int id, String title, String desc, String status, String contentType, String URL){
        String sql = "UPDATE Content SET title = ?, desc = ?, status = ?, content_type = ?, date_update = NOW(), url = ? WHERE id = ?";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL, id );
    }
    public void deleteContent(int id){
        String sql = "DELETE FROM Content WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    public Content getContent(int id){
        String sql = "SELECT * FROM Content WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, ContentJdbcTemplateRepository::mapRow);
    }
}
