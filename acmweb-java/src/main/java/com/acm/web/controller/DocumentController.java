package com.acm.web.controller;


import com.acm.web.entity.Document;
import com.acm.web.service.DocumentService;
import com.acm.web.utils.DelFileUtil;
import com.acm.web.utils.DownloadUtil;
import com.acm.web.utils.UploadUtil;
import com.acm.web.vo.DocumentVo;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Autowired
    UploadUtil uploadUtil;

    @Autowired
    DownloadUtil downloadUtil;

    @Autowired
    DelFileUtil delFileUtil;

    @PostMapping("/upload")
    public ResponseVo fileUpload(@RequestParam("file") MultipartFile file) {
        return uploadUtil.upload(file);
    }

    @GetMapping("/download/{fileName}")
    public void downloads(HttpServletResponse response, @PathVariable("fileName") String fileName) {
        downloadUtil.download(response, fileName);
    }

    @GetMapping("/file")
    public ResponseVo<DocumentVo> file() {
        QueryWrapper<Document> wrapper = new QueryWrapper<>();
        wrapper.eq("isDel", 0);
        DocumentVo fileVo = new DocumentVo()
                .setSum(documentService.count())
                .setFileList(documentService.list(wrapper));
        return ResponseVo.success(fileVo);
    }

    @GetMapping("/delFile/{id}")
    public ResponseVo delFile(@PathVariable("id") Integer id) {
        return delFileUtil.delFile(id);
    }
}
