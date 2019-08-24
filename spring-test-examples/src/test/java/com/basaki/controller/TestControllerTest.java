package com.basaki.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@code TestControllerTest} is a mock functional test for {@code
 * TestController}.
 * <p>
 * {@code WebMvcTest} doesn't work with {@code ManagementContextConfiguration}.
 * Ended up using {@code AutoConfigureMockMvc} with {@code SpringBootTest}.
 * <p/>
 *
 * @author Indra Basak
 * @since 1/27/18
 */
@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = {TestController.class}, secure = false)
@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGet() throws Exception {
        //MvcResult result = mvc.perform(get("/manage/test/get")
        MvcResult result = mvc.perform(get("/manage/test/get")
                .with(user("admin").password("secret"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertNotNull(content);
        assertThat(content,
                Matchers.is(Matchers.equalTo(
                        "{\"status\":200,\"message\":\"ok\"}")));
        System.out.println(content);
    }
}
