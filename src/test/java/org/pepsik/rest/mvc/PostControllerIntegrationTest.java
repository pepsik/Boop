package org.pepsik.rest.mvc;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.pepsik.Application;
import org.pepsik.core.models.entities.Account;
import org.pepsik.core.security.AccountUserDetails;
import org.pepsik.core.services.AccountService;
import org.pepsik.core.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

/**
 * Created by pepsik on 10/15/2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "development")
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@Sql(scripts = {"/database/populate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/database/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PostControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PostService postService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void createPostWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(post("/rest/posts").with(user(new AccountUserDetails(account)))
                .content("{\"title\":\"testTitleZ\",\"text\":\"testTextZ\"}")
                .contentType("application/json"))
                .andExpect(jsonPath("$.rid", is(4)))
                .andExpect(jsonPath("$.title", is("testTitleZ")))
                .andExpect(jsonPath("$.text", is("testTextZ")))
                .andExpect(jsonPath("$.author", is("username1")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isCreated());
    }

    @Test
    public void createPostWhenNotLoggedIn() throws Exception {
        mockMvc.perform(post("/rest/posts")
                .content("{\"title\":\"testTitle\",\"text\":\"testText\"}")
                .contentType("application/json"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getExistingPostById() throws Exception {
        mockMvc.perform(get("/rest/posts/1"))
                .andExpect(jsonPath("$.rid", is(1)))
                .andExpect(jsonPath("$.title", is("Title1")))
                .andExpect(jsonPath("$.text", is("Post1")))
                .andExpect(jsonPath("$.author", is("username1")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void getNonExistingPostById() throws Exception {
        mockMvc.perform(get("/rest/posts/1124"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllPosts() throws Exception {
        mockMvc.perform(get("/rest/posts"))
                .andExpect(jsonPath("$.posts[*].rid", hasItems(is(1), is(2))))
                .andExpect(jsonPath("$.posts[*].title", hasItems(is("Title1"), is("Title2"), is("Title3"))))
                .andExpect(jsonPath("$.posts[*].text", hasItems(is("Post1"), is("Post2"), is("Post3"))))
                .andExpect(jsonPath("$.posts[*].author", hasItems(is("username1"), is("username2"))))
                .andExpect(jsonPath("$.posts[*].when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void updateExistingPostWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(put("/rest/posts/1").with(user(new AccountUserDetails(account)))
                .content("{\"title\":\"titleUpdated\",\"text\":\"textUpdated\"}")
                .contentType("application/json"))
                .andExpect(jsonPath("$.rid", is(1)))
                .andExpect(jsonPath("$.title", is("titleUpdated")))
                .andExpect(jsonPath("$.text", is("textUpdated")))
                .andExpect(jsonPath("$.author", is("username1")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void updateExistingPostWhenLoggedInButNotAuthor() throws Exception {
        Account account = accountService.findAccountById(2L);

        mockMvc.perform(put("/rest/posts/1").with(user(new AccountUserDetails(account)))
                .content("{\"title\":\"titleUpdated\",\"text\":\"textUpdated\"}")
                .contentType("application/json"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void updateNonExistingPostWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(put("/rest/posts/1124").with(user(new AccountUserDetails(account)))
                .content("{\"title\":\"titleUpdated\",\"text\":\"textUpdated\"}")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExistingPostWhenNotLoggedIn() throws Exception {
        mockMvc.perform(put("/rest/posts/1")
                .content("{\"title\":\"testT\",\"text\":\"testT\"}")
                .contentType("application/json"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void updateNonExistingPostWhenNotLoggedIn() throws Exception {
        mockMvc.perform(put("/rest/posts/1124")
                .content("{\"title\":\"testT\",\"text\":\"testT\"}")
                .contentType("application/json"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Ignore //required Cascade delete option
    public void deleteExistingPostWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(delete("/rest/posts/1").with(user(new AccountUserDetails(account))))
                .andExpect(jsonPath("$.rid", is(1)))
                .andExpect(jsonPath("$.title", is("Title1")))
                .andExpect(jsonPath("$.text", is("Post1")))
                .andExpect(jsonPath("$.author", is("username1")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteExistingPostWhenLoggedInButNotAuthor() throws Exception {
        Account account = accountService.findAccountById(2L);

        mockMvc.perform(delete("/rest/posts/1").with(user(new AccountUserDetails(account))))
                .andExpect(status().isForbidden());
    }

    @Test
    public void deleteExistingPostWhenNotLoggedIn() throws Exception {
        mockMvc.perform(delete("/rest/posts/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteNonExistingPostWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(delete("/rest/posts/1124").with(user(new AccountUserDetails(account))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteNonExistingPostWhenNonLoggedIn() throws Exception {
        mockMvc.perform(delete("/rest/posts/1124"))
                .andExpect(status().isUnauthorized());
    }
}
