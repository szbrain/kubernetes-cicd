package com.koolearn.club.dto.wechat;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/3/14.
 */
@Getter
@Setter
public class GenerateQRVo extends BaseSerialization{
    private String scene;
    private String page;
}
