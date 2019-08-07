package com.koolearn.club.service;

import com.koolearn.club.dto.activity.*;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;

import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */

public interface IActivityService {

     JSONResult addActivity(ActivityReqDTO activityReqDTO);

     JSONPageResult<List<ActivityRespDTO>> activityList(ActivityListReqDTO activityListReqDTO);

     JSONResult<ActivityDetailRespDTO> activityDetail(ActivityDetailReqDTO activityDetailReqDTO);


     JSONResult editActivity(ActivityEditReqDTO activityEditReqDTO);


     JSONResult deleteAwardItem(Integer id);


     /**
      * 开始活动
      * @param id
      * @return
      */
     int startActivity(Integer id);

     /**
      *  结束活动
      * @param id
      * @return
      */
     int finishActivity(Integer id);
}
