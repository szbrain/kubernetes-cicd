package com.koolearn.club.dto.classroom;

import com.koolearn.club.entity.PeClassNotice;
import com.koolearn.club.entity.PeResource;
import com.koolearn.club.entity.PeTeacher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lilong01 on 2018/3/1.
 */
@Getter
@Setter
public class ClassNoticeDTO extends PeClassNotice{
    List<PeResource> resourceList;
    private PeTeacher teacher;

}
