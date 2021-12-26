package com.books.library.repos.maphelper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookDataExtractor implements ResultSetExtractor<Map<String, List<String>>> {
    @Override
    public Map<String, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, List<String>> bookData = new HashMap<>();
        while (rs.next()) {
            String bookTitle = rs.getString("title");
            String authorName = rs.getString("name");
            String genre = rs.getString("genre");
            bookData.put(bookTitle, List.of(genre, authorName));
        }
        return bookData;
    }
}
