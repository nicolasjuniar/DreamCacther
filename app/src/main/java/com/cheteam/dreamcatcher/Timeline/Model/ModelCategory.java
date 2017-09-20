package com.cheteam.dreamcatcher.Timeline.Model;

/**
 * Created by Nicolas Juniar on 17/09/2017.
 */

public class ModelCategory {
    String category;
    boolean cek;

    public ModelCategory(String category, boolean cek) {
        this.category = category;
        this.cek = cek;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelCategory that = (ModelCategory) o;

        return category != null ? category.equals(that.category) : that.category == null;

    }

    @Override
    public int hashCode() {
        return category != null ? category.hashCode() : 0;
    }
}
