package com.s3.t.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageResponse {
    private  byte[] content;
    private String fileUrl;
}
