package com.acm.web.service.impl;

import com.acm.web.entity.Introduce;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.mapper.IntroduceMapper;
import com.acm.web.service.IntroduceService;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseVo<IPage<Introduce>> introduces(Integer currentPage) {
        Page<Introduce> page = new Page(currentPage,5);
        IPage<Introduce> pageDate = this.page(page, new QueryWrapper<Introduce>().orderByDesc("created"));
        return ResponseVo.success(pageDate);
    }

    @Override
    public ResponseVo<List<Introduce>> introduceAdmin() {
        List<Introduce> list = this.list();
        if(list.size()>0) return ResponseVo.success(list);
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @Override
    public ResponseVo<Introduce> detail(Integer id) {
        Introduce introduce = this.getById(id);
        if(introduce==null){
            return ResponseVo.error(ResponseEnum.SELECT_EMPTY);
        }
        return ResponseVo.success(introduce);
    }

    @Override
    public ResponseVo edit(Introduce introduce) {
        Introduce temp = null;
        if(introduce.getId()!=null){
            temp = this.getById(introduce.getId());
        }else {
            temp = new Introduce();
            temp.setCreated(LocalDateTime.now());
        }
        BeanUtils.copyProperties(introduce,temp,"id","created");
        this.saveOrUpdate(temp);
        return ResponseVo.success();
    }
}
