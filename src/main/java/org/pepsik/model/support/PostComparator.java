package org.pepsik.model.support;

import org.pepsik.model.Post;

import java.util.Comparator;

public class PostComparator implements Comparator<Post> {

    @Override
    public int compare(Post post1, Post post2) {
        if (post1.getId() < post2.getId()) return -1;
        if (post1.getId() == post2.getId()) return 0;
        return 0;
    }
}
