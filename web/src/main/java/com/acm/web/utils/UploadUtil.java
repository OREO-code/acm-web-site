package com.acm.web.utils;


import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.FileService;
import com.acm.web.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Slf4j
@Component
public class UploadUtil {

    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    @Value("${spring.profiles.active}")
    private String profiles;

    @Autowired
    FileService fileService;

    public ResponseVo upload(MultipartFile file) {


        String path;
        if (profiles.equalsIgnoreCase("dev")) {
            path = System.getProperty("user.dir") + uploadDir + file.getOriginalFilename();
        }
        else path = uploadDir + file.getOriginalFilename();
        log.info("目录:{}", path);
        File realPath = new File(uploadDir);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            return ResponseVo.error(ResponseEnum.UPLOAD_ERROR);
        }
        return ResponseVo.success(ResponseEnum.UPLOAD_SUCCESS);
    }


}
