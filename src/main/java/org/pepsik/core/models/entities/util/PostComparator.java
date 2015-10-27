package org.pepsik.core.models.entities.util;

import org.pepsik.core.models.entities.Reworked.Post;

import java.util.Comparator;

public class PostComparator implements Comparator<Post> {

    @Override
    public int compare(Post post1, Post post2) {
        if (post1.getId() < post2.getId()) return -1;
        if (post1.getId() == post2.getId()) return 0;
        return 0;
    }
}
