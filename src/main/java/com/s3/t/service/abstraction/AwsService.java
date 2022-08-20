package com.s3.t.service.abstraction;

import com.s3.t.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface AwsService {
    Image uploadFile(MultipartFile file);

    List<String> getObjectsFromS3();

    InputStream downloadFile(String key);
}
