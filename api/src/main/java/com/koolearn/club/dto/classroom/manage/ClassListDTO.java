package com.koolearn.club.dto.classroom.manage;

import com.koolearn.club.entity.PeClass;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/4/24.
 */
@Getter
@Setter
public class ClassListDTO extends PeClass{
    private String teacher;
    private int classNum;
    private String identifyingCode;
}
