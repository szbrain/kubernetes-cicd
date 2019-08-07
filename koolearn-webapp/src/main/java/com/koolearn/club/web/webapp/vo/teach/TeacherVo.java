package com.koolearn.club.web.webapp.vo.teach;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.entity.PeTeacher;
import com.koolearn.framework.common.utils.PropertiesConfigUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/3/8.
 */
@Getter
@Setter
@ApiModel(value = "老师对象", description = "TeacherVo")
public class TeacherVo extends BaseSerialization {
    @ApiModelProperty(value = "老师昵称")
    private String nickname;
    @ApiModelProperty(value = "老师头像")
    private String avatar;

    public static TeacherVo getInstance(PeTeacher teacher){
        TeacherVo teacherVo = new TeacherVo();
        teacherVo.setNickname(teacher.getNickname());
        teacherVo.setAvatar(teacher.getAvatar());
        return teacherVo;
    }
    public void setAvatar(String avatar) {
        this.avatar = PropertiesConfigUtils.getProperty("cloud_image_host")+avatar;
    }
}
