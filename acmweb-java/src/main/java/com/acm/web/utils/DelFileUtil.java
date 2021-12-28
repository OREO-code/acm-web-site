package com.acm.web.utils;

import com.acm.web.entity.Document;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.DocumentService;
import com.acm.web.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class DelFileUtil {

    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    @Value("${spring.profiles.active}")
    private String profiles;

    @Autowired
    DocumentService fileService;

    public ResponseVo delFile(Integer id) {
        Document file = fileService.getById(id);
        String filename = file.getFileName();
        String path;
        file.setIsDel(1);
        try {
            fileService.updateById(file);
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.DELETE_ERROR);
        }
        if (profiles.equalsIgnoreCase("dev")) {
            path = System.getProperty("user.dir") + uploadDir + filename;
            File file1 = new File(path);
            if (file1.exists() && file1.isDirectory() && file1.delete()) {
                return ResponseVo.success("删除成功");
            } else return ResponseVo.error(ResponseEnum.DELETE_ERROR);
        } else {
            path = uploadDir + filename;
            String cmd = "rm -f" + path;
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (Exception e) {
                log.error("删除失败", e);
                return ResponseVo.error(ResponseEnum.DELETE_ERROR);
            }
        }
        return ResponseVo.success("删除成功");
    }
}
