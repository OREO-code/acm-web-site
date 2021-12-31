package com.acm.web.utils;

import com.acm.web.entity.Document;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Component
public class DownloadUtil {

    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    @Value("${spring.profiles.active}")
    private String profiles;

    private static final String FILEPATH = "/document/";

    public void download(HttpServletResponse response, String fileName) {
        String oldName = fileName;
        Document document = new Document().selectOne(new QueryWrapper<Document>().eq("fileName", fileName));
        if(document==null){
            return;
        }
        String[] strings = document.getFileUrl().split("/");
        fileName = strings[strings.length-1];
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        InputStream input = null;
        OutputStream output = null;
        byte[] buff = new byte[1024];
        String path ;
        if (profiles.equalsIgnoreCase("dev")) {
            path = System.getProperty("user.dir") + uploadDir + FILEPATH + fileName;
        }
        else path = uploadDir + FILEPATH + fileName ;
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
}
