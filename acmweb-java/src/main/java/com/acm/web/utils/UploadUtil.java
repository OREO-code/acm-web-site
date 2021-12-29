package com.acm.web.utils;


import com.acm.web.service.DocumentService;
import com.acm.web.service.RotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Component
public class UploadUtil {

    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    @Value("${spring.profiles.active}")
    private String profiles;

    //轮播图在服务器上的存放位置
    private static final String URL = "http://101.43.16.42:8082/rotation/";

    @Autowired
    DocumentService documentService;

    @Autowired
    RotationService rotationService;

    public String upload(MultipartFile multipartFile, String filePath) {

        String realpath;
        //主要原因就是不好控制 要加判断 如果存入static目录的话
        //如果是dev环境存在/resources/document/目录下！！！
        //否则存在/usr/local/document/目录下！！！
        if (profiles.equalsIgnoreCase("dev")) {
            realpath = System.getProperty("user.dir") + uploadDir + filePath;
        } else {
            realpath = uploadDir + filePath;
        }
        log.info("路径:{}", realpath+multipartFile.getOriginalFilename());
        File realPath = new File(realpath);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        try {
            multipartFile.transferTo(new File(realpath+multipartFile.getOriginalFilename()));
        } catch (Exception e) {
            log.error("上传意外错误:{}",e.getMessage());
            e.printStackTrace();
        }
        if (filePath.equals("document/")) return realpath+multipartFile.getOriginalFilename();
        return URL+multipartFile.getOriginalFilename();
    }
}
