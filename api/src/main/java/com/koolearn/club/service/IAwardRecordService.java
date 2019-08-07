package com.koolearn.club.service;


import com.koolearn.club.dto.award.*;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.utils.JSONPageResult;

import java.util.List;

public interface IAwardRecordService {


    PageDTO<AwardRecordListDTO> listForWebapp(AwardRecordListParamDTO awardRecordListParamDTO);

    List<AwardRecordListDTO> listAllForWebapp(AwardRecordListParamDTO awardRecordListParamDTO);
}
