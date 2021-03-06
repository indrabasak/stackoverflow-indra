package com.basaki.controller;

import com.basaki.annotation.EnableHttpLogging;
import com.basaki.annotation.Key;
import com.basaki.annotation.LogArguments;
import com.basaki.annotation.NewLogArguments;
import com.basaki.annotation.Wrappable;
import com.basaki.model.Book;
import com.basaki.model.MyClass;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code BookController} is the spring REST controller for book API.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/4/17
 */
@RestController
@Slf4j
@Api(value = "Book API",
        description = "Book API",
        produces = "application/json", tags = {"API"})
public class BookController {

    @Autowired
    private MyClass myClass;

    @ApiOperation(
            value = "Creates a book.",
            response = Book.class)
    @NewLogArguments
    @RequestMapping(method = RequestMethod.POST, value = "/books")
    public Book create(@RequestBody Book book) {
        return book;
    }

    @ApiOperation(
            value = "Retrieves a book.",
            notes = "Requires book identifier",
            response = Book.class)
    @RequestMapping(method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE}, value = "/books/{id}")
    @Wrappable
    @LogArguments
    @EnableHttpLogging
    public Book read(@Key @PathVariable("id") Integer id) {
        myClass.myMethod("hello");
        Book book = new Book();
        book.setId(id);
        book.setTitle(
                "The Pope of Physics: Enrico Fermi and the Birth of the Atomic Age");
        book.setAuthor("Gino Segrè and Bettina Hoerlin");

        return book;
    }
}
