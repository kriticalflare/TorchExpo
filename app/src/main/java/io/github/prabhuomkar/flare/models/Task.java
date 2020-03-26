package io.github.prabhuomkar.flare.models;

public class Task {
    private String name, description, imageLink;

    public Task(String name, String description, String imageLink) {
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageLink() {
        return imageLink;
    }
}
