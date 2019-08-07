package com.koolearn.club.service;

import com.koolearn.club.entity.PeResource;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface IResourceService {

    PeResource getById(int id);
    List<PeResource> listByIdsJson(String idsJson);

    /**
     * 批量更新资源
     * @param oldResourceIdsJson
     * @param newResourceList
     * @return
     */
    String batchUpdate(String oldResourceIdsJson, List<PeResource> newResourceList);

    /**
     *  批量插入
     * @param resourceList
     * @return
     */
    String batchInsert(List<PeResource> resourceList);
}
