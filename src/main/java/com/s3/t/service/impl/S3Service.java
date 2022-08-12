package com.s3.t.service.impl;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.s3.t.config.AWSConfig;
import com.s3.t.model.entity.IImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.IOException;

@Service
public class S3Service {
    @Autowired
    private AWSConfig amazonAwsConfig;
/*    private final static String BUCKET="s3demobucketnocountry";*/
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
        }
    }
}



/*
    @Autowired
    private AmazonS3Client s3client;

    *//*Envio imagen*//*
public String sendObject(MultipartFile multipartFile){
    String extension= StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
    String key=String.format("%s.%s", UUID.randomUUID(),extension);
    ObjectMetadata objectMetadata=new ObjectMetadata();
    objectMetadata.setContentType(multipartFile.getContentType());
    try {
        PutObjectRequest putObjectRequest=new PutObjectRequest(BUCKET, key, multipartFile.getInputStream(), objectMetadata);
        s3client.putObject(putObjectRequest);
        return key;
    }catch (IOException ex){
        throw new RuntimeException("Error en carga de archivo"+ex.getMessage());
    }


}
    public String getObjectUrl( String key){
        return String.format("https://%s.%s.amazonaws.com/%s",BUCKET,key);
    }

}*/
