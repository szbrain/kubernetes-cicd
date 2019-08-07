package com.koolearn.club.web.webapp.vo;

import com.koolearn.club.constants.commons.Constants;
import com.koolearn.club.dto.BaseSerialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/2/8.
 */
@Getter
@Setter
@ApiModel(value = "响应对象", description = "Result")
public class Result<T> extends BaseSerialization {

    @ApiModelProperty(value = "业务码")
    private int code;
    @ApiModelProperty(value = "响应消息")
    private String message;
    @ApiModelProperty(value = "响应数据")
    private T data;

    public Result(T data) {
        this.code = 0;
        this.message = Constants.REQUEST_SUCCESS;
        this.data = data;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
