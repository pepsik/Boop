package org.pepsik.core.repositories;

import org.pepsik.core.models.entities.Tag;

/**
 * Created by pepsik on 10/26/2015.
 */
public interface TagRepo {
    Tag create(Tag data);

    Tag find(String name);

    Tag findPosts(String name);

    Tag update(String name, Tag data);

    Tag delete(String name);
}
