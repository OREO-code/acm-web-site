package com.acm.web.service.impl;

import com.acm.web.entity.Document;
import com.acm.web.mapper.DocumentMapper;
import com.acm.web.service.DocumentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements DocumentService {

}
