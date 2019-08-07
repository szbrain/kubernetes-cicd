package com.koolearn.club.dto.comment;

import com.koolearn.club.entity.PeComment;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/5/7.
 */
@Getter
@Setter
public class CommentListDTO extends PeComment{
    private String stuName;
    private String replierName;
}
