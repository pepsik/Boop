package org.pepsik.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.core.services.Reworked.PostService;
import org.pepsik.rest.mvc.Reworked.PostController;
import org.pepsik.rest.utilities.PostList;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by pepsik on 9/30/2015.
 */
public class PostControllerTest {
    @InjectMocks
    private PostController postController;
    @Mock
    private PostService service;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void createPost() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("postTitle");
        post.setText("postText");
        post.setOwner(new Account(1L, "username", "password"));
        post.setWhen(LocalDateTime.now());

        when(service.createPost(any(String.class), any(Post.class))).thenReturn(post);

        mockMvc.perform(post("/rest/posts")
                .content("{\"title\":\"testT\",\"text\":\"testT\"}")
                .contentType("application/json"))
                .andExpect(jsonPath("$.rid", is(1)))
                .andExpect(jsonPath("$.title", is("postTitle")))
                .andExpect(jsonPath("$.text", is("postText")))
                .andExpect(jsonPath("$.author", is("username")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isCreated());
    }

    @Test
    public void getExistingPostById() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("postTitle");
        post.setText("postText");
        post.setOwner(new Account(1L, "username", "password"));
        post.setWhen(LocalDateTime.now());

        when(service.findPostById(1L)).thenReturn(post);

        mockMvc.perform(get("/rest/posts/1"))
                .andExpect(jsonPath("$.rid", is(1)))
                .andExpect(jsonPath("$.title", is("postTitle")))
                .andExpect(jsonPath("$.text", is("postText")))
                .andExpect(jsonPath("$.author", is("username")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void getNonExistingPostById() throws Exception {
        when(service.findPostById(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/post/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllPosts() throws Exception {
        Post postA = new Post();
        postA.setId(1L);
        postA.setTitle("postTitleA");
        postA.setText("postTextA");
        postA.setOwner(new Account(1L, "username", "password"));
        postA.setWhen(LocalDateTime.now());

        Post postB = new Post();
        postB.setId(2L);
        postB.setTitle("postTitleB");
        postB.setText("postTextB");
        postB.setOwner(new Account(1L, "username", "password"));
        postB.setWhen(LocalDateTime.now());

        PostList posts = new PostList(Arrays.asList(postA, postB));

        when(service.findAllPosts()).thenReturn(posts);

        mockMvc.perform(get("/rest/posts"))
                .andExpect(jsonPath("$.posts[*].rid", hasItems(is(1), is(2))))
                .andExpect(jsonPath("$.posts[*].title", hasItems(is("postTitleB"), is("postTitleA"))))
                .andExpect(jsonPath("$.posts[*].text", hasItems(is("postTextB"), is("postTextA"))))
                .andExpect(jsonPath("$.posts[*].author", hasItem("username")))
                .andExpect(jsonPath("$.posts[*].when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void updateExistingPost() throws Exception {
        Post postA = new Post();
        postA.setId(1L);
        postA.setTitle("postTitleA");
        postA.setText("postTextA");
        postA.setOwner(new Account(1L, "username", "password"));
        postA.setWhen(LocalDateTime.now());

        when(service.updatePost(any(Long.class), any(Post.class))).thenReturn(postA);

        mockMvc.perform(put("/rest/posts/1")
                .content("{\"title\":\"testT\",\"text\":\"testT\"}")
                .contentType("application/json"))
                .andExpect(jsonPath("$.rid", is(1)))
                .andExpect(jsonPath("$.title", is("postTitleA")))
                .andExpect(jsonPath("$.text", is("postTextA")))
                .andExpect(jsonPath("$.author", is("username")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void updateNonExistingPost() throws Exception {
        when(service.updatePost(any(Long.class), any(Post.class))).thenReturn(null);

        mockMvc.perform(put("/rest/posts/1")
                .content("{\"title\":\"testT\",\"text\":\"testT\"}")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteExistingPost() throws Exception {
        Post postA = new Post();
        postA.setId(1L);
        postA.setTitle("postTitleA");
        postA.setText("postTextA");
        postA.setOwner(new Account(1L, "username", "password"));
        postA.setWhen(LocalDateTime.now());

        when(service.deletePost(any(Long.class))).thenReturn(postA);

        mockMvc.perform(delete("/rest/posts/1"))
                .andExpect(jsonPath("$.rid", is(1)))
                .andExpect(jsonPath("$.title", is("postTitleA")))
                .andExpect(jsonPath("$.text", is("postTextA")))
                .andExpect(jsonPath("$.author", is("username")))
                .andExpect(jsonPath("$.when", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingPost() throws Exception {
        when(service.deletePost(any(Long.class))).thenReturn(null);

        mockMvc.perform(delete("/rest/posts/1"))
                .andExpect(status().isNotFound());
    }
}
