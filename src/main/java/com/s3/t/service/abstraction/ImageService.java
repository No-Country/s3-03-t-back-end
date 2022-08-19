package com.s3.t.service.abstraction;

import com.s3.t.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface ImageService {
    ArrayList<Image> imagesPost(ArrayList<MultipartFile> postImage);
}
