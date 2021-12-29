package com.books.library.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Genre {
    private int id;
    private String genre;
}
