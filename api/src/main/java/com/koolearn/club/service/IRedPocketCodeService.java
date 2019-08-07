package com.koolearn.club.service;

import com.koolearn.club.dto.redpocket.RedPocketCodeDTO;
import com.koolearn.club.dto.redpocket.RedPocketCodeReqDTO;
import com.koolearn.club.dto.redpocket.RedpocketCodeListReqDTO;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;

import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */

public interface IRedPocketCodeService {

    public JSONResult<RedPocketCodeDTO> getCode(RedPocketCodeReqDTO redPocketCodeReqDTO);

    public JSONPageResult<List<RedPocketCodeDTO>> list(RedpocketCodeListReqDTO redpocketCodeListReqDTO);

    public JSONResult checkCode(String code);

}
