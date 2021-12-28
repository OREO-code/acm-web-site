package com.acm.web.utils;


import com.acm.web.entity.Document;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.DocumentService;
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

    @Autowired
    DocumentService fileService;

    public ResponseVo upload(MultipartFile multipartFile) {

        String path;
        String realpath;
        if (profiles.equalsIgnoreCase("dev")) {
            path = System.getProperty("user.dir") + uploadDir + multipartFile.getOriginalFilename();
            realpath = System.getProperty("user.dir") + uploadDir;
        } else {
            path = uploadDir + multipartFile.getOriginalFilename();
            realpath = uploadDir;
        }
        log.info("目录:{}", path);
        File realPath = new File(realpath);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        Document file = new Document()
                .setFileName(multipartFile.getOriginalFilename())
                .setIsDel(0)
                .setFileUrl(path);
        try {
            fileService.save(file);
            multipartFile.transferTo(new File(path));
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.UPLOAD_ERROR);
        }
        return ResponseVo.success("上传成功");
    }
}
