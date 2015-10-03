package org.pepsik.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pepsik.core.services.Reworked.CommentService;
import org.pepsik.rest.mvc.Reworked.CommentController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

        mockMvc = MockMvcBuilders.standaloneSetup(CommentController.class).build();
    }

    @Test
    public void createComment() throws Exception {

    }

    @Test
    public void getCommentsByExistingPost() {
    }

    @Test
    public void getCommentsByNonExistingPost() {
    }

    @Test
    public void updateExistingComment() {
    }

    @Test
    public void updateNonExistingComment() {
    }

    @Test
    public void deleteExistingComment() {
    }

    @Test
    public void deleteNonExistingComment() {
    }
}
