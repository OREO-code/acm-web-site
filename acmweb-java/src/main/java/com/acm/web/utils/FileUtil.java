package com.acm.web.utils;

import com.acm.web.entity.Document;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Component
@Slf4j
public class FileUtil {
    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    @Value("${spring.profiles.active}")
    private String profiles;

    //轮播图在服务器上的存放位置
    private static final String URL = "http://101.43.16.42:8082/image/";

    public String upload(MultipartFile multipartFile, String filePath, String originalFilename) {

        String realpath;
        //主要原因就是不好控制 要加判断 如果存入static目录的话
        //如果是dev环境存在/resources/document/目录下！！！
        //否则存在/usr/local/document/目录下！！！
        if (profiles.equalsIgnoreCase("dev")) {
            realpath = System.getProperty("user.dir") + uploadDir + filePath;
        } else {
            realpath = uploadDir + filePath;
        }
        log.info("路径:{}", realpath + originalFilename);
        File realPath = new File(realpath);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        try {
            multipartFile.transferTo(new File(realpath + originalFilename));
        } catch (Exception e) {
            log.error("上传意外错误:{}", e.getMessage());
            e.printStackTrace();
        }
        if (filePath.contains("document")) return realpath + originalFilename;
        return URL + originalFilename;
    }

    public void download(HttpServletResponse response, String fileName) {
        Document document = new Document().selectOne(new QueryWrapper<Document>().eq("fileName", fileName));
        String oldName = document.getFileName();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        InputStream input = null;
        OutputStream output = null;
        byte[] buff = new byte[1024];
        String path ;
        if (profiles.equalsIgnoreCase("dev")) {
            path = System.getProperty("user.dir") + uploadDir + "/document" + fileName;
        }
        else path = document.getFileUrl() ;
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(oldName, "UTF-8"));
            input = new FileInputStream(file);
            output = response.getOutputStream();
            int index = 0;
            while ((index = input.read(buff)) != -1) {
                output.write(buff, 0, index);
                output.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean delFile(String fileName, String filePath) {
        String path;
        if (profiles.equalsIgnoreCase("dev")) {
            path = System.getProperty("user.dir") + uploadDir + filePath + fileName;
            File file1 = new File(path);
            return file1.exists() && file1.isFile() && file1.delete();
        } else {
            path = uploadDir + filePath + fileName;
            String cmd = "rm -f " + path;
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
