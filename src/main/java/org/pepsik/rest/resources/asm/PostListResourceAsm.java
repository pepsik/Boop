package org.pepsik.rest.resources.asm;

import org.pepsik.rest.mvc.PostController;
import org.pepsik.rest.resources.PostListResource;
import org.pepsik.rest.resources.PostResource;
import org.pepsik.rest.utilities.PostList;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

/**
 * Created by pepsik on 10/1/2015.
 */
public class PostListResourceAsm extends ResourceAssemblerSupport<PostList, PostListResource>{

    public PostListResourceAsm() {
        super(PostController.class, PostListResource.class);
    }

    @Override
    public PostListResource toResource(PostList postList) {
        List<PostResource> resList = new PostResourceAsm().toResources(postList.getPosts());
        PostListResource finalRes = new PostListResource();
        finalRes.setPosts(resList);
        return finalRes;
    }
}
