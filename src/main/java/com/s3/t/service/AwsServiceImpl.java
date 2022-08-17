package com.s3.t.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.s3.t.config.AwsConfig;
import com.s3.t.exception.UploadImageException;
import com.s3.t.model.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwsServiceImpl {
    @Autowired
    private AwsConfig amazonAwsConfig;

    public String upload(Image image) {
        try {
            AmazonS3 s3Client = amazonAwsConfig.initialize();
            String bucket = amazonAwsConfig.getBucket();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(image.getContentType());

            s3Client.putObject(
                    new PutObjectRequest(bucket,
                            image.getFileName(),
                            image.getInputStream(),
                            metadata).withCannedAcl(CannedAccessControlList.PublicRead));

            return s3Client.getUrl(bucket, image.getFileName()).toExternalForm();

        } catch (RuntimeException e) {
            throw new UploadImageException(e.getMessage());
        }
    }
}
