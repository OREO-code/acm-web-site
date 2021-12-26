package com.acm.web.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class UploadUtil {

    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    public void upload(MultipartFile file) throws IOException {
        log.info("目录:{}", uploadDir + file.getOriginalFilename());
        File realPath = new File(uploadDir);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        file.transferTo(new File(Objects.requireNonNull(file.getOriginalFilename())));
    }


}
