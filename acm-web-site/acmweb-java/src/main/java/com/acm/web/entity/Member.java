package com.acm.web.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Member extends Model<Member> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String year;

    private String college;

    private String honor;

    private String url;


}
