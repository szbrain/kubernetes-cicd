package com.koolearn.club.dto.menu;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lilong01 on 2018/6/5.
 */
@Getter
@Setter
public class MenuAclListRespDTO extends BaseSerialization{

    private int id;
    private String name;
    private boolean isCheck;
    private List<MenuAclListRespDTO> buttonList;

}
