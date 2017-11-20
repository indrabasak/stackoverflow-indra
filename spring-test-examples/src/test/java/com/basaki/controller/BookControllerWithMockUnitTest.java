package com.basaki.controller;

import com.basaki.controller.BookController;
import com.basaki.model.Book;
import com.basaki.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class BookControllerWithMockUnitTest {

    @Mock
    private BookService service;

    @InjectMocks
    private BookController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(
                new ServletRequestAttributes(request));
    }

    @Test
    public void testRead() {
        Book book = new Book(1, "Test Book", "Test Author", null);
        when(service.read(any(Integer.class))).thenReturn(book);
        Book retBook = controller.read(1);
        System.out.println(retBook);
    }
}
