package com.koolearn.club.dto.task;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lilong01 on 2018/3/1.
 */
@Getter
@Setter
public class PostLiveTaskDTO extends PostTaskDTO{
    private String title;
    private String coverUrl;
    private String speaker;
    private String introduction;
    private Date openTime;
    private Date endTime;
    private String pushUrl;
    private String playFlvUrl;
    private String playHlsUrl;
    private String playRtmpUrl;
    private String groupId;
}
