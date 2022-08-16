package com.s3.t.service;

import org.springframework.stereotype.Service;

@Service
public class AwsServiceImpl {
 /*   *//*@Autowired
    private AWSConfig amazonAwsConfig;
    *//**//*    private final static String BUCKET="s3demobucketnocountry";*//**//*
    public String upload(IImage image) {
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
            throw new RuntimeException("Error al cargar la imagen: " + e.getMessage());
        }*//*
    }*/
}
