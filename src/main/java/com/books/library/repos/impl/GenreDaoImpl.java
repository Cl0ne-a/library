package com.books.library.repos.impl;

import com.books.library.dto.Genre;
import com.books.library.repos.GenreDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Genre> viewGenres() {
        String sql = "select id, genre from genre";
        return jdbc.query(sql, new GenreMapper());
    }

    @Override
    public Genre getById(int id) {
        Map<String, Object> map = Collections.singletonMap("id", id);
        String sql = "select id, genre from genre where id = :id";
        return jdbc.queryForObject(sql,  map, new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String genre = rs.getString("genre");
            return Genre.builder().id(id).genre(genre).build();
        }
    }
}
