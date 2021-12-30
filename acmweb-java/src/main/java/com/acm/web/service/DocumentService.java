package com.acm.web.service;


import com.acm.web.entity.Document;
import com.acm.web.vo.DocumentVo;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface DocumentService extends IService<Document> {
    ResponseVo delFile(Integer id);

    ResponseVo<DocumentVo> file();

    void downloads(HttpServletResponse response, String fileName);

    ResponseVo fileUpload(MultipartFile file);
}
