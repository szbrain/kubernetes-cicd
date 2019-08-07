package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeResource;
import com.koolearn.framework.mybatis.annotation.DAO;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface ResourceMapper {
    PeResource getById(int id);

    /**
     * 新增资源
     * @param resource
     * @return
     */
    int insert(PeResource resource);

    /**
     * 批量逻辑删除资源
     * @param resourceIds
     * @return
     */
    int batchDelete(List<Integer> resourceIds);
}
