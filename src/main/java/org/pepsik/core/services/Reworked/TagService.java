package org.pepsik.core.services.Reworked;

import org.pepsik.core.models.entities.Reworked.Tag;
import org.pepsik.rest.utilities.PostList;

/**
 * Created by pepsik on 10/27/2015.
 */

public interface TagService {
    PostList getPostsByTag(String tag);

    Tag findTag(String name);

    Tag updateTag(String name, Tag data);

    Tag deleteTag(String name);
}
