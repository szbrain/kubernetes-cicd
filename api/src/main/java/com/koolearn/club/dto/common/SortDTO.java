package com.koolearn.club.dto.common;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/4/24.
 */
@Setter
@Getter
public class SortDTO extends BaseSerialization{
    private int businessId;
    private int sn;
}
