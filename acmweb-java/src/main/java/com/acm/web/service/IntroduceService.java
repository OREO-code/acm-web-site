package com.acm.web.service;

import com.acm.web.entity.Introduce;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author henrik
 * @since 2021-12-29
 */
public interface IntroduceService extends IService<Introduce> {

    ResponseVo<IPage<Introduce>> introduces(Integer currentPage);

    ResponseVo<List<Introduce>> introduceAdmin();

    ResponseVo<Introduce> detail(Integer id);

    ResponseVo edit(Introduce introduce);

    ResponseVo delIntroduce(Integer id);
}
