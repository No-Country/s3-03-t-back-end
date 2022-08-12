package com.s3.t.controller;

import com.s3.t.model.entity.IImage;
import com.s3.t.service.impl.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class S3Controller {
    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    Map<String, String> uploadS3(@RequestParam IImage file){
        String key=s3Service.upload(file);
        Map<String,String> result=new HashMap<>();
        result.put("key",key);
       /* result.put("url",s3Service.getObjectUrl(key));*/
        return result;
    }

}
