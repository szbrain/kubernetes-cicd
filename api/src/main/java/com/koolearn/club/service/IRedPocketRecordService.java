package com.koolearn.club.service;


import com.koolearn.club.dto.activity.RedPocketRecordListReqDTO;
import com.koolearn.club.dto.award.RedPocketRecordDTO;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;

import java.util.List;

public interface IRedPocketRecordService {


    /**
     * 红包中奖名单
     *
     * @param redPocketRecordListReqDTO
     * @return
     */
    JSONPageResult<List<RedPocketRecordDTO>> redPocketRecordList(RedPocketRecordListReqDTO redPocketRecordListReqDTO);

    /**
     * 导出红包中奖名单
     *
     * @param redPocketRecordListReqDTO
     * @return
     */
    JSONResult<List<RedPocketRecordDTO>> export(RedPocketRecordListReqDTO redPocketRecordListReqDTO);


}
