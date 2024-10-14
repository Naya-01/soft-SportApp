package models.enums;

public enum MediaType {

    VIDEO("Video"),
    IMAGE("Image");

    private String media;

    MediaType(String media) {
        this.media = media;
    }

    public String getMedia() {
        return media;
    }
}
