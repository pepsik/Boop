package org.pepsik.mvc;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.core.services.Reworked.PostService;
import org.pepsik.rest.mvc.Reworked.PostController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.endsWith;
import static org.mockito.Mockito.*;
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
    public void getExistingPostById() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("postTitle");
        post.setText("postText");
        post.setOwner(new Account(1L, "username", "password"));
        post.setWhen(LocalDateTime.now());

        when(service.findPostById(1L)).thenReturn(post);

        mockMvc.perform(get("/rest/post/1"))
                .andExpect(jsonPath("$.rid", is(1)))
                .andExpect(jsonPath("$.title", is("postTitle")))
                .andExpect(jsonPath("$.text", is("postText")))
                .andExpect(jsonPath("$.author", is("username")))
//                .andExpect(jsonPath("$.when", ))
                .andExpect(status().isOk());
    }
}
