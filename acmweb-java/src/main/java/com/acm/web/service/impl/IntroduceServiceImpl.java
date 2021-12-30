package com.acm.web.service.impl;

import com.acm.web.entity.Introduce;
import com.acm.web.mapper.IntroduceMapper;
import com.acm.web.service.IntroduceService;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author henrik
 * @since 2021-12-29
 */
@Service
public class IntroduceServiceImpl extends ServiceImpl<IntroduceMapper, Introduce> implements IntroduceService {

    @Override
    public ResponseVo<IPage> introduces(Integer currentPage) {
        Page page = new Page(currentPage,5);
        IPage pageDate = this.page(page, new QueryWrapper<Introduce>().orderByDesc("created"));
        return ResponseVo.success(pageDate);
    }
}
