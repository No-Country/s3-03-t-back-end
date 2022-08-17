package com.s3.t.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.s3.t.service.abstraction.AwsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AwsServiceImpl implements AwsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AwsServiceImpl.class);
    @Autowired
    private AmazonS3 amazonAwsConfig;
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Override
    public void uploadFile(MultipartFile file) {
        File mainFile = new File(file.getOriginalFilename());
        try (FileOutputStream stream = new FileOutputStream(mainFile)) {
            stream.write(file.getBytes());
            //cambiar nombre de archivo valor unico
            String newFileName = System.currentTimeMillis() + "_" + mainFile.getName();
            LOGGER.warn("Subiendo archivo con el nombre... " + newFileName); //info consola

            PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, mainFile);
            amazonAwsConfig.putObject(request);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    @Override
    public List<String> getObjectsFromS3() {
        ListObjectsV2Result result = amazonAwsConfig.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        return objects.stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    @Override
    public InputStream downloadFile(String key) {
        S3Object object = amazonAwsConfig.getObject(bucketName, key);
        return object.getObjectContent();
    }
}
