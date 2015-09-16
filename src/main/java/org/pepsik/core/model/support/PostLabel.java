package org.pepsik.core.model.support;

import org.pepsik.rest.mvc.SearchController;

/**
 * Used for simple search mechanism {@link SearchController SearchController}
 */

public class PostLabel {

    private long id;

    private String title;

    public PostLabel(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "PostLabel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
