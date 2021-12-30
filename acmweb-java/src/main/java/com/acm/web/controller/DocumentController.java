package com.acm.web.controller;


import com.acm.web.service.DocumentService;
import com.acm.web.vo.DocumentVo;
import com.acm.web.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @PostMapping("/upload")
    public ResponseVo fileUpload(@RequestParam("file") MultipartFile file) {
        return documentService.fileUpload(file);
    }

    //必须带后缀名
    //考虑从数据库里查,数据库里的数据带有后缀名
    @GetMapping("/download/{fileName}")
    public void downloads(HttpServletResponse response, @PathVariable("fileName") String fileName) {
        documentService.downloads(response, fileName);
    }

    @GetMapping("/file")
    public ResponseVo<DocumentVo> file() {
        return documentService.file();
    }

    @GetMapping("/delFile/{id}")
    public ResponseVo delFile(@PathVariable("id") Integer id) {
        return documentService.delFile(id);
    }
}
