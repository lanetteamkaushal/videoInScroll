package com.lanet.videoplay;

/**
 * Created by lcom75 on 3/1/17.
 */
public class SimpleObject {

    public String name = "GitHub Wikis is a simple way to let others contribute content. "
            + "Any GitHub user can create and edit pages to use for documentation, examples, "
            + "support, or anything you wish.";

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleObject that = (SimpleObject) o;

        return name.equals(that.name);
    }

    @Override public int hashCode() {
        return name.hashCode();
    }

    @Override public String toString() {
        return "SimpleObject{" +
                "name='" + name + '\'' +
                '}';
    }
}
