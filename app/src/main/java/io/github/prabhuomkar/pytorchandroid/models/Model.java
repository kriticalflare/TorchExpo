package io.github.prabhuomkar.pytorchandroid.models;

public class Model {
    private String name, description, paperLink, sourceLink, downloadLink, imageLink;

    public Model(String name, String description, String paperLink, String sourceLink,
                 String downloadLink, String imageLink) {
        this.name = name;
        this.description = description;
        this.paperLink = paperLink;
        this.sourceLink = sourceLink;
        this.downloadLink = downloadLink;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPaperLink() {
        return paperLink;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public String getImageLink() {
        return imageLink;
    }
}
