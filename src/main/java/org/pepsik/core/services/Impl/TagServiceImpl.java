package org.pepsik.core.services.Impl;

import org.pepsik.core.models.entities.Tag;
import org.pepsik.core.repositories.TagRepo;
import org.pepsik.core.services.TagService;
import org.pepsik.rest.utilities.PostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pepsik on 10/27/2015.
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepo tagRepo;

    @Override
    public PostList getPostsByTag(String name) {
        Tag tag = tagRepo.findPosts(name);
        return new PostList(tag.getPosts());
    }

    @Override
    public Tag findTag(String name) {
        return tagRepo.find(name);
    }

    @Override
    public Tag updateTag(String name, Tag data) {
        return tagRepo.update(name, data);
    }

    @Override
    public Tag deleteTag(String name) {
        return tagRepo.delete(name);
    }
}
