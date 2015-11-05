package org.pepsik.core.models.entities.util;

/**
 * Created by pepsik on 11/4/2015.
 */
public class PaginationSupport {
    private Long postCount;

    public PaginationSupport(Long pagesCount) {
        this.postCount = pagesCount;
    }

    public Long getPostCount() {
        return postCount;
    }

    public void setPostCount(Long pagesCount) {
        this.postCount = pagesCount;
    }
}
