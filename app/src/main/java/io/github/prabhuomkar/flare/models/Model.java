package io.github.prabhuomkar.flare.models;

public class Model {
    private String name, description, paperLink, sourceLink, downloadLink, imageLink,
            playgroundActivity;
    private int size;

    public Model(String name, String description, String paperLink, String sourceLink,
                 String downloadLink, String imageLink, String playgroundActivity, int size) {
        this.name = name;
        this.description = description;
        this.paperLink = paperLink;
        this.sourceLink = sourceLink;
        this.downloadLink = downloadLink;
        this.imageLink = imageLink;
        this.playgroundActivity = playgroundActivity;
        this.size = size;
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

    public String getPlaygroundActivity() {
        return playgroundActivity;
    }

    public int getSize() {
        return size;
    }
}
