package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.constants.ChannelTypeEnum;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.message.ChannelDTO;
import com.koolearn.club.entity.PeChannel;
import com.koolearn.club.entity.PeClass;
import com.koolearn.club.mapper.ChannelMapper;
import com.koolearn.club.mapper.ClassMapper;
import com.koolearn.club.service.IChannelService;
import com.koolearn.util.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class ChannelServiceImpl implements IChannelService {

    @Resource
    private ChannelMapper channelMapper;
    @Resource
    private ClassMapper classMapper;

    @Override
    public PeChannel getById(int id) {
        return channelMapper.getById(id);
    }

    @Override
    public PageDTO<ChannelDTO> listByTeachId(int teachId, int offset, int pageSize) {
        //初始化返回对象
        PageDTO<ChannelDTO> pageDTO = new PageDTO<>();
        List<ChannelDTO> channelDTOList = Lists.newArrayList();
        //分页查询频道列表
        List<PeChannel> channelList = channelMapper.listByTeachId(teachId, offset, pageSize);
        for (PeChannel channel : channelList) {
            ChannelDTO channelDTO = new ChannelDTO();
            BeanUtils.copyProperties(channelDTO, channel);
            //班级频道，需要查询班级名称
            if(channel.getType() == ChannelTypeEnum.CLASSROOM.getCode()){
                PeClass peClass = classMapper.getById(channel.getBusinessId());
                if(null != peClass){
                    channelDTO.setClassName(peClass.getName());
                    channelDTO.setClassId(peClass.getId());
                }
            }
            channelDTOList.add(channelDTO);
        }
        pageDTO.setList(channelDTOList);
        pageDTO.setCount(channelMapper.countByTeachId(teachId));
        return pageDTO;
    }

    @Override
    @Transactional
    public int createChannel(short type, int businessId) {
        PeChannel channel = new PeChannel();
        channel.setType(type);
        channel.setCreateTime(new Date());
        channel.setBusinessId(businessId);
        if(type == ChannelTypeEnum.CLASSROOM.getCode()){ //班级频道
            //频道封面为班级封面
            PeClass peClass = classMapper.getById(businessId);
            if(null != peClass){
                channel.setCoverUrl(peClass.getCoverUrl());
                channel.setTeachId(peClass.getTeachId());
            }
        }
        if(type == ChannelTypeEnum.ADD_STU.getCode()) { //新增学员
            channel.setTeachId(businessId);
        }
        if(type == ChannelTypeEnum.LEARN_STAT.getCode()) { //班级学习报表
            channel.setTeachId(businessId);
        }
        channelMapper.insert(channel);
        return channel.getId();
    }

}
