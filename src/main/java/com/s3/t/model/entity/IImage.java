package com.s3.t.model.entity;

import java.io.InputStream;

public interface IImage {
    String getFileName();

    String getContentType();

    InputStream getInputStream();
}
