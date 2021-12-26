package com.acm.web.controller;


import com.acm.web.enums.ResponseEnum;
import com.acm.web.utils.DownloadUtil;
import com.acm.web.utils.UploadUtil;
import com.acm.web.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class FileController {

    @Autowired
    UploadUtil uploadUtil;

    @Autowired
    DownloadUtil downloadUtil;

    @PostMapping("/upload")
    public ResponseVo fileUpload(@RequestParam("file") MultipartFile file){
        try {
            uploadUtil.upload(file);
        } catch (IOException e) {
            return ResponseVo.error(ResponseEnum.UPLOAD_ERROR);
        }
        return ResponseVo.success(ResponseEnum.UPLOAD_SUCCESS);
    }

    @GetMapping("/download/{fileName}")
    public void downloads(HttpServletResponse response, @PathVariable("fileName")String fileName) {
        downloadUtil.download(response, fileName);
    }
}
