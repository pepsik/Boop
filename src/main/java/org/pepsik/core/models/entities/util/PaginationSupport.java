package org.pepsik.core.models.entities.util;

/**
 * Created by pepsik on 11/4/2015.
 */
public class PaginationSupport {
    private Long countItems;

    public PaginationSupport(Long pagesCount) {
        this.countItems = pagesCount;
    }

    public Long getCountItems() {
        return countItems;
    }

    public void setCountItems(Long pagesCount) {
        this.countItems = pagesCount;
    }
}
