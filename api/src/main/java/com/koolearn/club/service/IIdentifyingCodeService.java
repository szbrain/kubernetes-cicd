package com.koolearn.club.service;

import com.koolearn.club.dto.identifying.IdentifyingCodeDTO;
import com.koolearn.club.dto.identifying.IdentifyingCodeListReqDTO;
import com.koolearn.club.dto.identifying.IdentifyingCodeReqDTO;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;

import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */

public interface IIdentifyingCodeService {

    public JSONResult<IdentifyingCodeDTO> getCode(IdentifyingCodeReqDTO identifyingCodeReqDTO);

    public JSONPageResult<List<IdentifyingCodeDTO>> list(IdentifyingCodeListReqDTO identifyingCodeListReqDTO);

    public JSONResult checkCode(String code);

}
