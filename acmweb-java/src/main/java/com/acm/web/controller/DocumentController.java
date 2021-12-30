package com.acm.web.controller;


import com.acm.web.entity.Document;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.DocumentService;
import com.acm.web.utils.DelFileUtil;
import com.acm.web.utils.DownloadUtil;
import com.acm.web.utils.IdUtil;
import com.acm.web.utils.UploadUtil;
import com.acm.web.vo.DocumentVo;
import com.acm.web.vo.FileVo;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

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

    private static final String FILEPATH = "document/";

    @PostMapping("/upload")
    public ResponseVo fileUpload(@RequestParam("file") MultipartFile file) {
        String id = String.valueOf(IdUtil.nextId());
        String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        //TODO 询问业务逻辑
        //TODO 待完善,当前只能通过后缀名判断
        if (!split[1].equals("txt") && !split[1].equals("md")) {
            return ResponseVo.error(ResponseEnum.UPLOAD_TYPE_ILLEGAL);
        }
        String path = uploadUtil.upload(file, FILEPATH, id + "." +split[1]);
        Document document = new Document()
                .setFileName(id + split[1])
                .setIsDel(0)
                .setFileUrl(path);
        try {
            documentService.save(document);
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.UPLOAD_ERROR);
        }
        return ResponseVo.success(new FileVo().setUrl(path));
    }

    //必须带后缀名
    //考虑从数据库里查,数据库里的数据带有后缀名
    @GetMapping("/download/{fileName}")
    public void downloads(HttpServletResponse response, @PathVariable("fileName") String fileName) {
        downloadUtil.download(response, fileName);
    }

    @GetMapping("/file")
    public ResponseVo<DocumentVo> file() {
        QueryWrapper<Document> wrapper = new QueryWrapper<>();
        wrapper.eq("isDel", 0);
        DocumentVo documentVo = new DocumentVo()
                .setSum(documentService.count())
                .setFileList(documentService.list(wrapper));
        return ResponseVo.success(documentVo);
    }

    @GetMapping("/delFile/{id}")
    public ResponseVo delFile(@PathVariable("id") Integer id) {
        String filename;
        try {
            Document document = documentService.getById(id);
            filename = document.getFileName();
            document.setIsDel(1);
            documentService.updateById(document);
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.DELETE_ERROR);
        }
        boolean flag = delFileUtil.delFile(filename, FILEPATH);
        if (flag) return ResponseVo.success("删除成功");
        return ResponseVo.error(ResponseEnum.DELETE_ERROR);
    }
}
