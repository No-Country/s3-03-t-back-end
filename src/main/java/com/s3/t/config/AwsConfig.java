package com.s3.t.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
    @Value("${aws.s3.bucket}")
    private String bucket;
    @Value ("${aws.s3.region}")
    private String region;
    @Value("${aws.s3.key.access}")
    private String accessKey;
    @Value("${aws.s3.key.secret}")
    private String secretKey;

    @Bean
    public AmazonS3 initialize() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    public String getBucket() {
        return this.bucket;
    }
}
