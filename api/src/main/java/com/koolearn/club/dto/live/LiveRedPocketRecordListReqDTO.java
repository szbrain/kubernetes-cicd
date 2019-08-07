package com.koolearn.club.dto.live;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
public class LiveRedPocketRecordListReqDTO extends BaseSerialization {

    private static final long serialVersionUID = -3699654381923360152L;
    /**
     * 任务ID
     */
    private int taskId;

    /**
     * 页码
     */
    private int offset;

    /**
     * 分页大小
     */
    private int pageSize;


}
