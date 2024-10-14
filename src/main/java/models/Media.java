package models;

import models.enums.MediaType;

import java.io.File;

public class Media {
    private File media;
    private MediaType type;

    public Media(File media) {
        this.media = media;
    }

    public File getMedia() {
        return this.media;
    }
}
