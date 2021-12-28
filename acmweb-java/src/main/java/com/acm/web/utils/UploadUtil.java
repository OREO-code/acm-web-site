package com.acm.web.utils;


import com.acm.web.entity.Document;
import com.acm.web.entity.Rotation;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.DocumentService;
import com.acm.web.service.RotationService;
import com.acm.web.vo.FileVo;
import com.acm.web.vo.ResponseVo;
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

    public ResponseVo upload(MultipartFile multipartFile, String filePath) {

        String path;
        String realpath;
        //主要原因就是不好控制 要加判断 如果存入static目录的话
        //如果是dev环境存在/resources/document/目录下！！！
        //否则存在/usr/local/document/目录下！！！
        if (profiles.equalsIgnoreCase("dev")) {
            path = System.getProperty("user.dir") + uploadDir + filePath + multipartFile.getOriginalFilename();
            realpath = System.getProperty("user.dir") + uploadDir + filePath;
        } else {
            path = uploadDir + filePath + multipartFile.getOriginalFilename();
            realpath = uploadDir + filePath;
        }
        log.info("路径:{}", path);
        File realPath = new File(realpath);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        if (filePath.equals("document/")) {
            Document document = new Document()
                    .setFileName(multipartFile.getOriginalFilename())
                    .setIsDel(0)
                    .setFileUrl(path);
            try {
                documentService.save(document);
                multipartFile.transferTo(new File(path));
                FileVo fileVo = new FileVo().setUrl(path);
                return ResponseVo.success(fileVo);
            } catch (Exception e) {
                return ResponseVo.error(ResponseEnum.UPLOAD_ERROR);
            }
        } else {
            Rotation rotation = new Rotation()
                    .setUrl(URL+multipartFile.getOriginalFilename())
                    .setIsDel(0)
                    .setName(multipartFile.getOriginalFilename());
            try {
                rotationService.save(rotation);
                multipartFile.transferTo(new File(path));
                FileVo fileVo = new FileVo().setUrl(URL+multipartFile.getOriginalFilename());
                return ResponseVo.success(fileVo);
            } catch (Exception e) {
                return ResponseVo.error(ResponseEnum.UPLOAD_ERROR);
            }
        }

    }
}
