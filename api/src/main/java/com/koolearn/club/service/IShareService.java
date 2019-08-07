package com.koolearn.club.service;

import com.koolearn.club.dto.share.CardParamDTO;

/**
 * created by ray
 */
public interface IShareService {

    boolean qrCodeUrlExist(int stuId,int classId);

    /**
     *
     * @param stuId
     * @param classId
     * @param url cos bucket url
     */
    int saveOrUpdateQRCodeUrl(int stuId, int classId, String url);

    CardParamDTO getCardParams(int stuId, int classId);

    CardParamDTO getCardParamsForTeach(int classId);

    boolean qrCodeUrlExistForTeach(int classId);

    int saveOrUpdateQRCodeUrlForTeach(int classId, String url);
}
