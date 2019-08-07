package com.koolearn.club.dto.live;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 抽奖请求对象
 */
@Getter
@Setter
public class LiveRedPocketReqDTO implements Serializable{

    private static final long serialVersionUID = -960383267568894013L;
    /**
     * 学生SID
     */
    private Integer stuId;

    /**
     * 任务ID
     */
    private Integer taskId;

    /**
     * 直播红包ID
     */
    private Integer liveRedPocketId;


    @Override
    public String toString() {
        return "LiveRedPocketReqDTO{" +
                "stuId=" + stuId +
                ", taskId=" + taskId +
                ", liveRedPocketId=" + liveRedPocketId +
                '}';
    }
}
