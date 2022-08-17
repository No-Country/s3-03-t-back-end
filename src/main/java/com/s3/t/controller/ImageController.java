package com.s3.t.controller;

import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/image")

public class ImageController {
/*    Map<String, String> uploadS3(@RequestParam IImage file){
        String key=s3Service.upload(file);
        Map<String,String> result=new HashMap<>();
        result.put("key",key);
         result.put("url",s3Service.getObjectUrl(key));
        return result;
    }
  @PostMapping("/upload")
    public ResponseEntity<ImageResponse> upload(@Valid @RequestBody ImageRequest request){
        ImageResponse response = ImageService.upload(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }*/
}
