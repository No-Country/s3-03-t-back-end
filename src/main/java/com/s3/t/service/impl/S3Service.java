package com.s3.t.service.impl;


import com.amazonaws.services.s3.AmazonS3Client;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.util.UUID;

@Service
public class S3Service {
    private final static String BUCKET="s3demobucketnocountry";
    /*private AmazonS3 s3client;*/

    @Autowired
    private AmazonS3Client s3client;
  /*  @Value("${ENDPOINT_URL}")*/
    private String endpointUrl;
/*    @Value("${BUCKET_NAME}")*/
    private String bucketName;
/*    @Value("${ACCESS_KEY}")*/
    private String accessKey;
/*    @Value("${SECRET_KEY}")*/
    private String secretKey;

    /*Envio imagen*/
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
/*    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }
    //Convert multipart file to file
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    //Generate name for the file
    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
    //Upload file to the bucket
    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
    //Method to be call from Controller
    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }
    public AWSResponse uploadFileBase64(MultipartFile multipartFile) {
        String fileUrl = "";

        try {
            File file = convertMultiPartToFile(multipartFile);

            String fileName = this.generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            this.uploadFileTos3bucket(fileName, file);

            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AWSResponse response = new AWSResponse(fileUrl);
        return response;
    }*/
}
