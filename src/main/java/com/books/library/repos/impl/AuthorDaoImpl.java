package com.books.library.repos.impl;

import com.books.library.dto.Author;
import com.books.library.repos.AuthorDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Author> viewAuthors() {
        String sql = "select id, name from author";
        return jdbc.query(sql, new AuthorMapper());
    }

    @Override
    public Author getById(int id) {
        Map<String, Object> map = Collections.singletonMap("id", id);
        String sql = "select id, name from author where id = :id";
        return jdbc.queryForObject(sql, map, new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return Author.builder().id(id).name(name).build();
        }
    }
}
