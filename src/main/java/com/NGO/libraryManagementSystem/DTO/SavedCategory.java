package com.NGO.libraryManagementSystem.DTO;

public class SavedCategory {
    private Integer id;
    private String name;

    public SavedCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
