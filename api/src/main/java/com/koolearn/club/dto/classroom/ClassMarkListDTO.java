package com.koolearn.club.dto.classroom;

import com.koolearn.club.entity.PeClassComment;
import com.koolearn.club.entity.PeClassMark;
import com.koolearn.club.entity.PeStudent;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/2/28.
 */
@Getter
@Setter
public class ClassMarkListDTO extends PeClassComment {
    private PeStudent student;
}
