package com.basaki.model;

import com.basaki.controller.BookController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BookControllerWithMockUnitTest {

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
        Book book = controller.read(1);
        System.out.println(book);
    }
}
