package org.pepsik.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Comment;
import org.pepsik.core.services.Reworked.CommentService;
import org.pepsik.rest.mvc.Reworked.CommentController;
import org.pepsik.rest.utilities.CommentList;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Matcher;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by pepsik on 10/3/2015.
 */
public class CommentControllerTest {
    @InjectMocks
    private CommentController commentController;
    @Mock
    private CommentService service;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    public void createComment() throws Exception {
        Comment commentA = new Comment();
        commentA.setId(2L);
        commentA.setText("text");
        commentA.setOwner(new Account(1L, "user", "pass"));
        commentA.setWhen(LocalDateTime.now());

        when(service.createComment(any(Long.class), any(String.class), any(Comment.class)))
                .thenReturn(commentA);

        mockMvc.perform(post("/rest/posts/2/comments")
                .content("{\"text\":\"testT\"}")
                .contentType("application/json"))
                .andExpect(jsonPath("$.rid", is(2)))
                .andExpect(jsonPath("$.text", is("text")))
                .andExpect(jsonPath("$.author", is("user")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isCreated());
    }

    @Test
    public void getCommentsByExistingPost() throws Exception {
        Comment commentA = new Comment();
        commentA.setId(2L);
        commentA.setText("textA");
        commentA.setOwner(new Account(1L, "userA", "pass"));
        commentA.setWhen(LocalDateTime.now());

        Comment commentB = new Comment();
        commentB.setId(3L);
        commentB.setText("textB");
        commentB.setOwner(new Account(1L, "userB", "pass"));
        commentB.setWhen(LocalDateTime.now());

        CommentList commentList = new CommentList(Arrays.asList(commentA, commentB));

        when(service.findAllCommentsByPost(any(Long.class))).thenReturn(commentList);

        mockMvc.perform(get("/rest/posts/1/comments"))
                .andExpect(jsonPath("$.comments[*].rid", hasItems(is(2), is(3))))
                .andExpect(jsonPath("$.comments[*].text", hasItems(is("textB"), is("textA"))))
                .andExpect(jsonPath("$.comments[*].author", hasItems(is("userA"), is("userB"))))
                .andExpect(jsonPath("$.comments[*].when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void getCommentsByNonExistingPost() throws Exception {
        when(service.findAllCommentsByPost(any(Long.class))).thenReturn(new CommentList(null));

        mockMvc.perform(get("/rest/posts/1/comments"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExistingComment() throws Exception {
        Comment commentB = new Comment();
        commentB.setId(3L);
        commentB.setText("textB");
        commentB.setOwner(new Account(1L, "userB", "pass"));
        commentB.setWhen(LocalDateTime.now());

        when(service.updateComment(any(Long.class), any(Comment.class))).thenReturn(commentB);

        mockMvc.perform(put("/rest/posts/1/comments/1")
                .content("{\"text\":\"test\"}")
                .contentType("application/json"))
                .andExpect(jsonPath("$.rid", is(3)))
                .andExpect(jsonPath("$.text", is("textB")))
                .andExpect(jsonPath("$.author", is("userB")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void updateNonExistingComment() throws Exception {
        when(service.updateComment(any(Long.class), any(Comment.class))).thenReturn(null);

        mockMvc.perform(put("/rest/posts/1/comments/1")
                .content("{\"text\":\"test\"}")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteExistingComment() throws Exception {
        Comment commentB = new Comment();
        commentB.setId(3L);
        commentB.setText("textB");
        commentB.setOwner(new Account(1L, "userB", "pass"));
        commentB.setWhen(LocalDateTime.now());

        when(service.deleteComment(any(Long.class))).thenReturn(commentB);

        mockMvc.perform(delete("/rest/posts/1/comments/1")
                .content("{\"text\":\"test\"}")
                .contentType("application/json"))
                .andExpect(jsonPath("$.rid", is(3)))
                .andExpect(jsonPath("$.text", is("textB")))
                .andExpect(jsonPath("$.author", is("userB")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingComment() throws Exception {
        when(service.deleteComment(any(Long.class))).thenReturn(null);

        mockMvc.perform(delete("/rest/posts/1/comments/1")
                .content("{\"text\":\"test\"}")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }
}
