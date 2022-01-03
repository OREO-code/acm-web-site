package com.acm.web.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import com.acm.web.entity.Document;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.mapper.DocumentMapper;
import com.acm.web.service.DocumentService;
import com.acm.web.utils.FileUtil;
import com.acm.web.utils.IdUtil;
import com.acm.web.vo.DocumentVo;
import com.acm.web.vo.FileVo;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements DocumentService {

    @Autowired
    FileUtil fileUtil;

    private static final String FILEPATH = "document/";

    @Override
    public ResponseVo delFile(Integer id) {
        String filename;
        try {
            Document document = this.getById(id);
            String[] strings = document.getFileUrl().split("/");
            filename = strings[strings.length - 1];
            document.setIsDel(1);
            this.updateById(document);
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.DELETE_ERROR);
        }

        boolean flag = fileUtil.delFile(filename, FILEPATH);
        if (flag) return ResponseVo.success("删除成功");
        return ResponseVo.error(ResponseEnum.DELETE_ERROR);
    }

    @Override
    public ResponseVo<DocumentVo> file() {
        QueryWrapper<Document> wrapper = new QueryWrapper<>();
        wrapper.eq("isDel", 0);
        DocumentVo documentVo = new DocumentVo()
                .setSum(this.count(wrapper))
                .setFileList(this.list(wrapper));
        return ResponseVo.success(documentVo);
    }

    @Override
    public ResponseVo fileUpload(MultipartFile file) {
        String id = String.valueOf(IdUtil.nextId());
        String path;
        try {
            InputStream inputStream = file.getInputStream();
            String type = FileTypeUtil.getType(inputStream, file.getOriginalFilename());
            if (!type.equals("mp4") && !type.equals("pdf")) {
                return ResponseVo.error(ResponseEnum.UPLOAD_TYPE_ILLEGAL);
            }
            path = fileUtil.upload(file, FILEPATH, id + "." + type);
            Document document = new Document()
                    .setFileName(file.getOriginalFilename())
                    .setIsDel(0)
                    .setFileUrl(path);
            this.save(document);
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.UPLOAD_ERROR);
        }
        return ResponseVo.success(new FileVo().setUrl(path));
    }

    @Override
    public void downloads(HttpServletResponse response, String fileName) {
        fileUtil.download(response, fileName);
    }
}
