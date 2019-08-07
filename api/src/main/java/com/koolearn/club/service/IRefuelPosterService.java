package com.koolearn.club.service;

import com.koolearn.club.dto.refuelposter.RefuelPosterDTO;
import com.koolearn.club.dto.refuelposter.RefuelPosterKeywordsDTO;
import com.koolearn.club.entity.PeRefuelPoster;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface IRefuelPosterService {
    PeRefuelPoster getById(int id);

    /**
     * 查询关键字，默认查出3条数据
     * @return
     * @param keywordIds
     */
    List<RefuelPosterKeywordsDTO> listKeywords(String keywordIds);

    /**
     * 生成加油海报
     * @param studentId
     * @param epithet
     * @param collegeName
     * @param keywordId
     * @return
     */
    Integer generate(int studentId, String epithet, String collegeName, Integer keywordId);

    /**
     * 通过sid查询加油海报
     * @param sid
     * @return
     */
    RefuelPosterDTO getByStuId(int sid);
}
