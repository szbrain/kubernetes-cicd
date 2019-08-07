package com.koolearn.club.dto.assistant;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lilong01 on 2018/6/5.
 */
@Getter
@Setter
public class AssistantEditReqDTO extends BaseSerialization{
    private int id;
    private String name;
    private String phone;
    private String remark;
    private List<Integer> menuIdList;
}
