package org.pepsik.model.support;

import org.pepsik.model.Favorite;

import java.util.Comparator;

/**
 * Created by pepsik on 5/27/15.
 */
public class FavoriteComparator implements Comparator<Favorite> {

    @Override
    public int compare(Favorite o1, Favorite o2) {
        if (o1.getId() < o2.getId()) return -1;
        if (o1.getId() == o2.getId()) return 0;
        return 1;
    }
}
