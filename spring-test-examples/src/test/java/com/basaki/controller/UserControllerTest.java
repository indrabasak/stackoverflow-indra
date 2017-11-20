package com.basaki.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * {@code UserControllerTest} is the test class for {@code UnitController}. It
 * is example of validating against multiple JsonPaths when of them might be
 * true.
 * <p/>
 *
 * @author Indra Basak
 * @since 11/20/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under
        // test.
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGet() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"))
                .andReturn();

        int userId = 9;

        ResultMatcher matcher1 =
                jsonPath("$.[*].contributors[*].id", hasItem(userId));

        boolean matches1 = true;
        try {
            matcher1.match(result);
        } catch (AssertionError e) {
            matches1 = false;
        }

        ResultMatcher matcher2 =
                jsonPath("$.[*].creator", hasItem(userId));

        boolean matches2 = true;
        try {
            matcher2.match(result);
        } catch (AssertionError e) {
            matches2 = false;
        }

        assertTrue(matches1 || matches2);
    }
}
