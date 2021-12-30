package com.acm.web.service;

import com.acm.web.entity.Rotation;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.RotationVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;


public interface RotationService extends IService<Rotation> {

    ResponseVo<RotationVo> getRotation();

    ResponseVo updateRotation(Rotation rotation);

    ResponseVo delRotation(Rotation rotation);

    ResponseVo uploadRotation(MultipartFile file);
}
