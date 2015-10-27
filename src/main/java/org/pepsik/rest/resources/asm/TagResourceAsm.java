package org.pepsik.rest.resources.asm;

import org.pepsik.core.models.entities.Reworked.Tag;
import org.pepsik.rest.mvc.reworked.TagController;
import org.pepsik.rest.resources.TagResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by pepsik on 10/27/2015.
 */
public class TagResourceAsm extends ResourceAssemblerSupport<Tag, TagResource> {

    public TagResourceAsm() {
        super(TagController.class, TagResource.class);
    }

    @Override
    public TagResource toResource(Tag tag) {
        TagResource res = new TagResource();
        res.setRid(tag.getId());
        res.setName(tag.getName());
        res.setDescription(tag.getDescription());
        res.setImageUrl(tag.getImageUrl());
        res.setAuthor(tag.getAuthor().getUsername());
        res.setCreateDate(tag.getCreateDate());
        res.setPostsCount(tag.getPostsCount());
        res.add(linkTo(methodOn(TagController.class).getTag(tag.getName())).withSelfRel());
        return res;
    }
}
