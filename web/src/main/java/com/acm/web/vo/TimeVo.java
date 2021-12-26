package com.acm.web.vo;

import com.acm.web.entity.Time;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TimeVo {

    private Integer sum;

    private List<Time> timeList;
}
