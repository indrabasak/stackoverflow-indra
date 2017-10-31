package com.basaki.service;

import com.basaki.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private LanguageService service;

    public Book read(Integer id) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(
                "The Pope of Physics: Enrico Fermi and the Birth of the Atomic Age");
        book.setAuthor("Gino Segrè and Bettina Hoerlin");
        book.setLanguage(service.read("en-us"));

        return book;
    }
}
