package org.pepsik.rest.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.pepsik.Application;
import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.security.AccountUserDetails;
import org.pepsik.core.services.Reworked.AccountService;
import org.pepsik.core.services.Reworked.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "development")
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@Sql(scripts = {"/database/populate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/database/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CommentControllerIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommentService commentService;

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
    public void createCommentToExistingPostWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(post("/rest/posts/1/comments").with(user(new AccountUserDetails(account)))
                .content("{\"text\":\"TextZ\"}")
                .contentType("application/json"))
                .andExpect(jsonPath("$.rid", is(4)))
                .andExpect(jsonPath("$.text", is("TextZ")))
                .andExpect(jsonPath("$.author", is("username1")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isCreated());
    }

    @Test
    public void createCommentToNonExistingPostWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(post("/rest/posts/1124/comments").with(user(new AccountUserDetails(account)))
                .content("{\"text\":\"testText\"}")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createCommentToExistingPostWhenNonLoggedIn() throws Exception {
        mockMvc.perform(post("/rest/posts/1/comments")
                .content("{\"text\":\"testText\"}")
                .contentType("application/json"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void createCommentToNonExistingPostWhenNonLoggedIn() throws Exception {
        mockMvc.perform(post("/rest/posts/1124/comments")
                .content("{\"text\":\"testText\"}")
                .contentType("application/json"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getAllCommentsByExistingPost() throws Exception {
        mockMvc.perform(get("/rest/posts/1/comments"))
                .andExpect(jsonPath("$.comments[*].rid", hasItems(is(2), is(3))))
                .andExpect(jsonPath("$.comments[*].text", hasItems(is("Comment3"), is("Comment2"))))
                .andExpect(jsonPath("$.comments[*].author", hasItems(is("username1"))))
                .andExpect(jsonPath("$.comments[*].when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllCommentsByNonExistingPost() throws Exception {
        mockMvc.perform(get("/rest/posts/1124/comments"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExistingCommentWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(put("/rest/posts/1/comments/2").with(user(new AccountUserDetails(account)))
                .content("{\"text\":\"textUpdated\"}")
                .contentType("application/json"))
                .andExpect(jsonPath("$.rid", is(2)))
                .andExpect(jsonPath("$.text", is("textUpdated")))
                .andExpect(jsonPath("$.author", is("username1")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void updateExistingCommentWhenLoggedInButNotAuthor() throws Exception {
        Account account = accountService.findAccountById(2L);

        mockMvc.perform(put("/rest/posts/1/comments/2").with(user(new AccountUserDetails(account)))
                .content("{\"text\":\"textUpdated\"}")
                .contentType("application/json"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void updateNonExistingCommentWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(put("/rest/posts/1/comments/1124").with(user(new AccountUserDetails(account)))
                .content("{\"text\":\"textUpdated\"}")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateNonExistingCommentToNonExistingPostWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(put("/rest/posts/1124/comments/2").with(user(new AccountUserDetails(account)))
                .content("{\"text\":\"textUpdated\"}")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExistingCommentWhenNotLoggedIn() throws Exception {
        mockMvc.perform(put("/rest/posts/1/comments/2")
                .content("{\"text\":\"textUpdated\"}")
                .contentType("application/json"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void updateNonExistingCommentWhenNotLoggedIn() throws Exception {
        mockMvc.perform(put("/rest/posts/1/comments/1124")
                .content("{\"text\":\"textUpdated\"}")
                .contentType("application/json"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void updateNonExistingPostNonExistingCommentWhenNotLoggedIn() throws Exception {
        mockMvc.perform(put("/rest/posts/1124/comments/2")
                .content("{\"text\":\"textUpdated\"}")
                .contentType("application/json"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteExistingCommentWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(delete("/rest/posts/1/comments/2").with(user(new AccountUserDetails(account))))
                .andExpect(jsonPath("$.rid", is(2)))
                .andExpect(jsonPath("$.text", is("Comment2")))
                .andExpect(jsonPath("$.author", is("username1")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteExistingCommentWhenLoggedInButNotAuthor() throws Exception {
        Account account = accountService.findAccountById(2L);

        mockMvc.perform(delete("/rest/posts/1/comments/2").with(user(new AccountUserDetails(account))))
                .andExpect(status().isForbidden());
    }

    @Test
    public void deleteNonExistingCommentWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(delete("/rest/posts/1/comments/1124").with(user(new AccountUserDetails(account))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteNonExistingCommentNonExistingPostWhenLoggedIn() throws Exception {
        Account account = accountService.findAccountById(1L);

        mockMvc.perform(delete("/rest/posts/1124/comments/2").with(user(new AccountUserDetails(account))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteExistingCommentWhenNotLoggedIn() throws Exception {
        mockMvc.perform(delete("/rest/posts/1/comments/2"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteNonExistingCommentWhenNotLoggedIn() throws Exception {
        mockMvc.perform(delete("/rest/posts/1/comments/1124"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteNonExistingCommentNotExistingPostWhenNotLoggedIn() throws Exception {
        mockMvc.perform(delete("/rest/posts/1124/comments/2"))
                .andExpect(status().isUnauthorized());
    }
}
