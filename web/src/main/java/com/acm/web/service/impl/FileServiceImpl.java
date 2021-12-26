package com.acm.web.service.impl;

import com.acm.web.entity.File;
import com.acm.web.mapper.FileMapper;
import com.acm.web.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}
