package com.ascending.demo.api.controller;

import com.ascending.demo.api.service.impl.AWSS3ServiceImpl;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/aws")
public class AWSController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AWSS3ServiceImpl awss3Service;

    @RequestBody
    @RequestMapping(value = "/pic", method = RequestMethod.POST)
    public Map<String, String> uploadPicture() {
        try{
            awss3Service.uploadObject();
        } catch (ServiceException e) {
            logger.error("error on saving record", e);
        }
    }


    @RequestMapping(value = "/pic", method = RequestMethod.POST,consumes = {"multipart/form-data"})
    public String uploadPicture(@RequestParam(value = "pic") MultipartFile picture) {
        try {
            String url = awss3Service.uploadMultipartFile(picture);
            return url;
        } catch (ServiceException e) {
            logger.error("not able to save file into storage", e);
            return null;
        }
    }
}
