package org.pepsik.core.persistence;

import org.pepsik.core.model.Tag;

/**
 * Created by pepsik on 5/26/15.
 */

public interface TagDao {

    void createTag(Tag tag);

    Tag getTag(String name);

    void updateTag(Tag tag);

    void deleteTag(Tag tag);

    boolean isExistTag(String name);
}
