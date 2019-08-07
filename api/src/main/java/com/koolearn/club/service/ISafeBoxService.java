package com.koolearn.club.service;

import com.koolearn.club.dto.safebox.SafeBoxEditReqDTO;
import com.koolearn.club.dto.safebox.SafeBoxListReqDTO;
import com.koolearn.club.dto.safebox.SafeBoxReqDTO;
import com.koolearn.club.dto.safebox.SafeBoxRespDTO;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;

import java.util.List;

public interface ISafeBoxService {


    JSONResult<SafeBoxRespDTO> get(SafeBoxReqDTO safeBoxReqDTO);

    JSONResult edit(SafeBoxEditReqDTO safeBoxEditReqDTO);

    JSONPageResult<List<SafeBoxRespDTO>> getList(SafeBoxListReqDTO safeBoxListReqDTO);

    /**
     * 保存或更新
     * @param stuId
     * @param imageUrl
     *@param imageContent
     * @return
     */
    int saveOrUpdate(int stuId, String imageUrl, String imageContent);

    List<SafeBoxRespDTO> listNoPage(SafeBoxListReqDTO safeBoxListReqDTO);

    /**
     *  通过stuId删除保险箱记录
     * @param stuId
     * @return
     */
    int deleteByStuId(int stuId);
}
