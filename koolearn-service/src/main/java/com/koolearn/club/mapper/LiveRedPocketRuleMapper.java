package com.koolearn.club.mapper;


import com.koolearn.club.entity.PeLiveRedPocketRule;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DAO
public interface LiveRedPocketRuleMapper {

    PeLiveRedPocketRule getById(int id);

    /**
     * 保存
     * @param liveRedPocketRule
     * @return
     */
    int insert(PeLiveRedPocketRule liveRedPocketRule);

    /**
     * 通过任务ID和状态查询红包规则
     * @param taskId
     * @param status
     * @return
     */
    PeLiveRedPocketRule getByTaskIdAndStatus(@Param("taskId") int taskId, @Param("status") short status);

    /**
     * 通过ID 更新状态
     * @param status
     * @param id
     * @return
     */
    int updateStatus(@Param("status") short status, @Param("id") int id);

    /**
     * 通过任务ID 查询红包规则
     * @param taskId
     * @return
     */
    List<PeLiveRedPocketRule> listByTaskId(int taskId);

    /**
     * 根据任务ID查找发放进行中的红包
     * @param taskId
     * @return
     */
    PeLiveRedPocketRule getGoingByTaskID(@Param("taskId") Integer taskId);
}