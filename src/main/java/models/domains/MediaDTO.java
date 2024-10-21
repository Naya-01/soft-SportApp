package models.domains;

import models.enums.MediaType;

public class MediaDTO {
    private String url;
    private MediaType type;

    public MediaDTO() {}

    public MediaDTO(String url, MediaType type) {
        this.url = url;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Media{" +
                "url='" + url + '\'' +
                ", type=" + type +
                '}';
    }
}
