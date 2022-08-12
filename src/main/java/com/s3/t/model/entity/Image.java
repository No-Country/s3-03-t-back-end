package com.s3.t.model.entity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

public class Image implements IImage {

    private final String fileName;
    private final String contentType;
    private final InputStream decodedImage;


    private Image(String fileName, String contentType, String encodedImage) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.decodedImage = decodeImage(encodedImage);
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public InputStream getInputStream() {
        return decodedImage;
    }
    /*Coficador de imagen*/
    private InputStream decodeImage(String encodedImage) {
        return new ByteArrayInputStream(Base64.getDecoder().decode(encodedImage));
    }
}
