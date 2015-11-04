package org.pepsik;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pepsik on 11/4/2015.
 */
public class tests {
    public static void main(String[] args) {
        Set<A> aSet = new HashSet<>();
        aSet.add(new A(1, null));
        aSet.add(new A(2, null));

        System.out.println(aSet);
        aSet.add(new A(1, "1"));
        System.out.println(aSet);
    }
}

class A {
    private long id;
    private String name;

    public A(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        A a = (A) o;

        return id == a.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
