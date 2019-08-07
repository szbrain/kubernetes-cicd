package com.koolearn.club.dto.activity;

import com.koolearn.club.entity.PeActivity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class ActivityRespDTO extends PeActivity{

    private static final long serialVersionUID = -1386179863569769456L;

    /**
     * 参与人数
     */
    //int  joinUserTotal;
    /**
     * 参与次数
     */
    //int joinTotal;

}
