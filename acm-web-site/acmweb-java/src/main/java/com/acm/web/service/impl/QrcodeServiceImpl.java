package com.acm.web.service.impl;

import com.acm.web.entity.Qrcode;
import com.acm.web.mapper.QrcodeMapper;
import com.acm.web.service.QrcodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class QrcodeServiceImpl extends ServiceImpl<QrcodeMapper, Qrcode> implements QrcodeService {

}
