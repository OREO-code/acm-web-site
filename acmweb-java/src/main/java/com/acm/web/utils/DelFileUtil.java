package com.acm.web.utils;

import lombok.extern.slf4j.Slf4j;
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

    public boolean delFile(String filename, String filePath) {
        String path;
        if (profiles.equalsIgnoreCase("dev")) {
            path = System.getProperty("user.dir") + uploadDir + filePath + filename;
            File file1 = new File(path);
            return file1.exists() && file1.isFile() && file1.delete();
        } else {
            path = uploadDir + filePath + filename;
            String cmd = "rm -f" + path;
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (Exception e) {
                log.error("删除失败:{}", e.getMessage());
                return false;
            }
        }
        return true;
    }
}
