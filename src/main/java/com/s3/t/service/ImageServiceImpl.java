package com.s3.t.service;

import com.s3.t.model.entity.Image;
import com.s3.t.service.abstraction.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public ArrayList<Image> imagesPost(ArrayList<MultipartFile> postImage) {
        return null;
    }
}
