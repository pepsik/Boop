package org.pepsik.model.support;

import java.io.Serializable;

/**
 * Created by pepsik on 5/27/15.
 */
public class FavoriteAssociationsId implements Serializable{

    private long userId;

    private long postId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteAssociationsId that = (FavoriteAssociationsId) o;

        if (userId != that.userId) return false;
        if (postId != that.postId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (postId ^ (postId >>> 32));
        return result;
    }
}
