package com.s3.t.controller;

import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;
import com.s3.t.service.abstraction.AwsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/image")

public class ImageController {
    @Autowired
    private AwsService awsService;
    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestPart(value="file") MultipartFile file) {
        awsService.uploadFile(file);
        String response = "El archivo "+file.getOriginalFilename()+" fue cargado correctamente a S3";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
