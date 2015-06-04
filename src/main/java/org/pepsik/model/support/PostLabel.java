package org.pepsik.model.support;

/**
 * Created by pepsik on 6/3/15.
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