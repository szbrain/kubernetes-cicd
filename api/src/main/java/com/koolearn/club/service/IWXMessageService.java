package com.koolearn.club.service;

import com.koolearn.club.entity.PeWXMessageRecord;
import com.koolearn.club.utils.JSONResult;

/**
 * Created by lvyangjun on 2018/3/30.
 */

public interface IWXMessageService {

    JSONResult add(PeWXMessageRecord peWXMessageRecord);

}
