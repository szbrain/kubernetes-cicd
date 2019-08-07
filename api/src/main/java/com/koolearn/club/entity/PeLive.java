package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lilong01 on 2018/2/27.
 */
@Getter
@Setter
public class PeLive extends BaseSerialization{

    private static final long serialVersionUID = 9164880061228146523L;

    private int id;
    private int taskId;
    private short status;
    private String pushUrl;
    private String playFlvUrl;
    private String playHlsUrl;
    private String playRtmpUrl;
    private String coverUrl;
    private String title;
    private String speaker;
    private String introduction;
    private String notice;
    private Date openTime;
    private Date endTime;
    private String imGroupId;
    private int onlineCount;
    private Date onlineTime;
    private Date createTime;
    private Date updateTime;






}
