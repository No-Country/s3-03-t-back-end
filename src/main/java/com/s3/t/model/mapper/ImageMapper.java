package com.s3.t.model.mapper;

import com.s3.t.model.entity.Image;
import com.s3.t.model.response.ImageResponse;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public ImageResponse imageToDto(Image img){
        return ImageResponse.builder()
                .id(img.getId())
                .name(img.getFileName())
                .fileUrl(img.getImageUrl())
                .build();
    }

}
