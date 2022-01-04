package com.acm.web.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import com.acm.web.entity.Member;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.form.QueryMembers;
import com.acm.web.mapper.MemberMapper;
import com.acm.web.service.MemberService;
import com.acm.web.utils.FileUtil;
import com.acm.web.utils.IdUtil;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;


@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    FileUtil fileUtil;

    private static final String FILEPATH = "image/";

    @Override
    public ResponseVo<List<Member>> members(QueryMembers queryMembers) {
        Member member = new Member();
        List<String> yearList = queryMembers.getYearList();
        List<String> collegeList = queryMembers.getCollegeList();
        List<Member> memberList = new ManagedList<>();
        if (yearList == null || collegeList == null) {
            return ResponseVo.error(ResponseEnum.PARAM_ERROR);
        } else if (yearList.size() == 0 && collegeList.size() == 0) {
            return ResponseVo.error(ResponseEnum.NEED_PARAM);
        } else if (collegeList.size() > 0 && yearList.size() == 0) {
            for (String college : collegeList) {
                QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("college", college);
                List<Member> members = member.selectList(queryWrapper);
                memberList.addAll(members);
            }
        } else if (collegeList.size() == 0) {
            for (String year : yearList) {
                QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("year", year);
                List<Member> members = member.selectList(queryWrapper);
                memberList.addAll(members);
            }
        } else {
            for (String year : yearList) {
                for (String college : collegeList) {
                    QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("year", year).eq("college", college);
                    List<Member> members = member.selectList(queryWrapper);
                    memberList.addAll(members);
                }
            }

        }
        return ResponseVo.success(memberList);
    }

    @Override
    public ResponseVo<List<String>> getYear() {
        List<String> yearList = memberMapper.getYear();
        return ResponseVo.success(yearList);
    }

    @Override
    public ResponseVo<List<String>> getCollege() {
        List<String> collegeList = memberMapper.getCollege();
        return ResponseVo.success(collegeList);
    }

    @Override
    public ResponseVo addMember(Member member) {
        boolean ans = this.save(member);
        if (ans) return ResponseVo.success("增加成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @Override
    public ResponseVo updateMember(Member member) {
        int ans = memberMapper.updateById(member);
        if (ans > 0) return ResponseVo.success("修改成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @Override
    public ResponseVo delMember(Integer id) {
        boolean ans = this.removeById(id);
        if (ans) return ResponseVo.success("删除成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @Override
    public ResponseVo<String> addMember(MultipartFile file) {
        String id = String.valueOf(IdUtil.nextId());
        String path;
        try {
            InputStream inputStream = file.getInputStream();
            String type = FileTypeUtil.getType(inputStream, file.getOriginalFilename());
            if (!type.equals("jpg") && !type.equals("png")) {
                return ResponseVo.error(ResponseEnum.UPLOAD_TYPE_ILLEGAL);
            }
            path = fileUtil.upload(file, FILEPATH, id + "." + type);
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.UPLOAD_ERROR);
        }
        return ResponseVo.success(path);
    }

    @Override
    public ResponseVo<List<Member>> getAll() {
        List<Member> list = this.list();
        if(list.size()>0) return ResponseVo.success(list);
        return ResponseVo.error(ResponseEnum.ERROR);
    }
}
