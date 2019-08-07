package com.koolearn.club.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.koolearn.club.entity.PeResource;
import com.koolearn.club.mapper.ResourceMapper;
import com.koolearn.club.service.IResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class ResourceServiceImpl implements IResourceService {

    @Resource
    private ResourceMapper resourceMapper;

    @Override
    public PeResource getById(int id) {

        return resourceMapper.getById(id);
    }
    @Override
    public List<PeResource> listByIdsJson(String idsJson){
        List<PeResource> resourceList = Lists.newArrayList();
        if(StringUtils.isNoneBlank(idsJson)){
            List<Integer> resourceIds = JSON.parseArray(idsJson,Integer.class);
            for(Integer resourceId : resourceIds){
                PeResource resource = resourceMapper.getById(resourceId);
                resourceList.add(resource);
            }
        }
        return resourceList;
    }

    @Override
    @Transactional
    public String batchUpdate(String oldResourceIdsJson, List<PeResource> newResourceList){
        //删除资源表中原来的记录
        if(StringUtils.isNoneBlank(oldResourceIdsJson)){
            List<Integer> resourceIds = JSON.parseArray(oldResourceIdsJson, Integer.class);
            if (resourceIds.size()>0){
                resourceMapper.batchDelete(resourceIds);
            }
        }
        //插入任务内对应资源
        List<Integer> resourceIds = Lists.newArrayList();
        if(null != newResourceList){
            for(PeResource resource : newResourceList){
                resource.setCreateTime(new Date());
                resourceMapper.insert(resource);
                resourceIds.add(resource.getId());
            }
        }
        return JSON.toJSONString(resourceIds);
    }

    @Override
    @Transactional
    public String batchInsert(List<PeResource> resourceList){
        //插入任务内对应资源
        List<Integer> resourceIds = Lists.newArrayList();
        if(null != resourceList && resourceList.size() > 0){
            for(PeResource resource : resourceList){
                resource.setCreateTime(new Date());
                resourceMapper.insert(resource);
                resourceIds.add(resource.getId());
            }
        }
        return JSON.toJSONString(resourceIds);
    }


}
