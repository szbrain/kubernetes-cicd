package com.koolearn.club.mapper;


import com.koolearn.club.dto.safebox.SafeBoxListReqDTO;
import com.koolearn.club.entity.PeSafeBox;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DAO
public interface SafeBoxMapper {

    /**
     * 添加
     */
    int add(PeSafeBox peSafeBox);

    /**
     * 修改
     */
    int edit(PeSafeBox peSafeBox);

    /**
     * 根据学生ID
     *
     * @return
     */
    PeSafeBox getByStuId(@Param("stuId") Integer stuId);

    /**
     * 列表查询
     *
     * @return
     */
    List<PeSafeBox> getList(SafeBoxListReqDTO safeBoxListReqDTO);


    /**
     * 列表count
     *
     * @return
     */
    Integer getListCount(SafeBoxListReqDTO safeBoxListReqDTO);


    /**
     * 列表查询
     *
     * @return
     */
    List<PeSafeBox> listNoPage(SafeBoxListReqDTO safeBoxListReqDTO);


    /**
     * 删除保险箱记录
     * @param stuId
     * @return
     */
    int deleteByStuId(int stuId);
}
