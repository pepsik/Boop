package org.pepsik.model;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by pepsik on 5/16/15.
 */

@Entity
public class Favorite {

    private List<Post> favorites;

    public List<Post> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Post> favorites) {
        this.favorites = favorites;
    }
}
