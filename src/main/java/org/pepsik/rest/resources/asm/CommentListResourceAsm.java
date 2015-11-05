package org.pepsik.rest.resources.asm;

import org.pepsik.rest.mvc.CommentController;
import org.pepsik.rest.resources.CommentListResource;
import org.pepsik.rest.resources.CommentResource;
import org.pepsik.rest.utilities.CommentList;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

/**
 * Created by pepsik on 10/3/2015.
 */
public class CommentListResourceAsm extends ResourceAssemblerSupport<CommentList, CommentListResource> {

    public CommentListResourceAsm() {
        super(CommentController.class, CommentListResource.class);
    }

    @Override
    public CommentListResource toResource(CommentList commentList) {
        List<CommentResource> resList = new CommentResourceAsm().toResources(commentList.getComments());
        CommentListResource finalRes = new CommentListResource();
        finalRes.setComments(resList);
        return finalRes;
    }
}
