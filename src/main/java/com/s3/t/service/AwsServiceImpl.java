package com.s3.t.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.s3.t.model.entity.Image;
import com.s3.t.service.abstraction.AwsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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
    private String bucketName ;
    private String newFileName="";

    public String nameNewFile(MultipartFile file,File newfile){
        try (FileOutputStream stream = new FileOutputStream(newfile)) {
            stream.write(file.getBytes()); //multipartFile
            newFileName = System.currentTimeMillis() + "_" + newfile.getName();
        }catch (IOException e)
        {   throw new RuntimeException("Error: Conversion de archivo" + e.getMessage()); }
        return newFileName;
    }

    @Override
    @Transactional
    public Image uploadFile(MultipartFile file) {
        newFileName=nameNewFile(file,converterFile(file));
        try {
            amazonAwsConfig.putObject(new PutObjectRequest(bucketName, newFileName, converterFile(file))
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            converterFile(file).delete();

            LOGGER.warn("URL: " + amazonAwsConfig.getUrl(bucketName, newFileName));

        }catch (AmazonS3Exception e){
            converterFile(file).delete();
             throw new AmazonServiceException("Error: No se cargo el archivo en aws"+ e.getMessage());
        }
        return Image.builder()
                .fileName(newFileName)
                .imageUrl(amazonAwsConfig.getUrl(bucketName, newFileName).toString())
                        .build();
    }
    public File converterFile(MultipartFile file){
       return new File(file.getOriginalFilename());
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
