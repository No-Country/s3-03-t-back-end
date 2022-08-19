package com.s3.t.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ImageResponse {
    private  Long id;
    private String fileUrl;
    private String name;
}
