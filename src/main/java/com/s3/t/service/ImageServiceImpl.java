package com.s3.t.service;

import com.s3.t.model.entity.Image;
import com.s3.t.service.abstraction.AwsService;
import com.s3.t.service.abstraction.ImageService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final AwsService awsService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
    @Override
    @Transactional
    public List<Image> imagesPost(List<MultipartFile> postImage) {
        List<Image> imagesPost=new ArrayList<>();
        for (MultipartFile m: postImage ) {
            imagesPost.add(awsService.uploadFile(m));

            //TODO:caso de error guardado de imagen deletear
        }
        LOGGER.warn("Array de amazon creado "+imagesPost.size());
        return imagesPost;
    }
//TODO:cada multipartfile guar  do en aws, el retorno, genero imagen , guardo y retorno


}
