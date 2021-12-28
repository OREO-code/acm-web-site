package com.acm.web.controller;


import com.acm.web.entity.File;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.FileService;
import com.acm.web.utils.DownloadUtil;
import com.acm.web.utils.UploadUtil;
import com.acm.web.vo.FileVo;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    UploadUtil uploadUtil;

    @Autowired
    DownloadUtil downloadUtil;

    @PostMapping("/upload")
    public ResponseVo fileUpload(@RequestParam("file") MultipartFile file) {
        return uploadUtil.upload(file);
    }

    @GetMapping("/download/{fileName}")
    public void downloads(HttpServletResponse response, @PathVariable("fileName") String fileName) {
        downloadUtil.download(response, fileName);
    }

    @GetMapping("/file")
    public ResponseVo<FileVo> file() {
        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper.eq("isDel",0);
        FileVo fileVo = new FileVo()
                .setSum(fileService.count())
                .setFileList(fileService.list(wrapper));
        return ResponseVo.success(fileVo);
    }

    @GetMapping("/delFile/{id}")
    public ResponseVo delFile(@PathVariable("id") Integer id) {
        File file = new File();
        file.setIsDel(1);
        file.setId(id);
        boolean flag = fileService.updateById(file);
        if (flag) return ResponseVo.success("删除成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }
}
