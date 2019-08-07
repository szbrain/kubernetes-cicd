package com.koolearn.club.dto.award;

import com.koolearn.club.entity.PeDrawRecord;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class AwardRecordListDTO extends PeDrawRecord {

    private static final long serialVersionUID = -3582008205165939700L;

    /**
     *  昵称
     */
    private String nickname;

}
