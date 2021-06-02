package be.stefan.todolist.models;

import java.util.ArrayList;

public class Category {
    private long categoryId;
    private String name;
    //TODO Add color



    public Category(String name) {
        this.categoryId = 0;
        this.name = name;
    }

    public Category(long categoryId, String name) {
        this(name);
        this.categoryId = categoryId;
    }


    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
