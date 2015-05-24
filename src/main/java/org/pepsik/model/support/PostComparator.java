package org.pepsik.model.support;

import org.pepsik.model.Post;

import java.util.Comparator;

/**
 * Created by pepsik on 5/24/15.
 */
public class PostComparator implements Comparator<Post> {

    @Override
    public int compare(Post o1, Post o2) {
        if (o1.getId() < o2.getId()) return -1;
        if (o1.getId() == o2.getId()) return 0;
        return 0;
    }
}
