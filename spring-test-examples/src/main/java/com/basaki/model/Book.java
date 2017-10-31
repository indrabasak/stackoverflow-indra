package com.basaki.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@code Book} represents a book entity.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/4/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;

    private String title;

    private String author;

    @JsonSetter(nulls= Nulls.AS_EMPTY)
    private Language language;
}
