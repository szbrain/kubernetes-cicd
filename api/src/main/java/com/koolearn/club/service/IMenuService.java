package com.koolearn.club.service;

import com.koolearn.club.dto.menu.MenuAclListReqDTO;
import com.koolearn.club.dto.menu.MenuAclListRespDTO;
import com.koolearn.club.dto.menu.MenuListRespDTO;
import com.koolearn.club.entity.PeMenu;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface IMenuService {
    PeMenu getById(int id);

    /**
     * 查询菜单列表
     * @return
     */
    List<MenuListRespDTO> list();

    List<MenuAclListRespDTO> list(MenuAclListReqDTO menuAclListReqDTO);
}
