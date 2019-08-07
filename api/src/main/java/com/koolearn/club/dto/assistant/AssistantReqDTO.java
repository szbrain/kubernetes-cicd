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
public class AssistantReqDTO extends BaseSerialization{
    private int classId;
    private String name;
    private String phone;
    private String remark;
    private List<Integer> menuIdList;

}
