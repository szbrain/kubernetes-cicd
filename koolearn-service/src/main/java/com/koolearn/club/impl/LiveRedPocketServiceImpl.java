package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.constants.LiveRedPocketRuleEnum;
import com.koolearn.club.constants.live.LiveRedPocketErrorCodeEnum;
import com.koolearn.club.constants.student.StudentErrorCodeEnum;
import com.koolearn.club.dto.live.CheckOpenRespDTO;
import com.koolearn.club.dto.live.LiveRedPocketRecordDTO;
import com.koolearn.club.dto.live.LiveRedPocketRecordRespDTO;
import com.koolearn.club.dto.live.LiveRedPocketReqDTO;
import com.koolearn.club.entity.*;
import com.koolearn.club.mapper.*;
import com.koolearn.club.service.ILiveRedPocketService;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.util.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class LiveRedPocketServiceImpl implements ILiveRedPocketService {


    @Resource
    private StudentMapper studentMapper;

    @Resource
    private LiveRedPocketRecordMapper liveRedPocketRecordMapper;

    @Resource
    private LiveRedPocketRuleMapper liveRedPocketRuleMapper;

    @Resource
    private StudentAccountMapper studentAccountMapper;

    @Resource
    private LiveRedPocketSendMapper liveRedPocketSendMapper;

    /**
     * 开红包
     *
     * @param liveRedPocketReqDTO
     * @return
     */
    @Override
    @Transactional
    public JSONResult<LiveRedPocketRecordDTO> open(LiveRedPocketReqDTO liveRedPocketReqDTO) {

        PeStudent student = studentMapper.getById(liveRedPocketReqDTO.getStuId());
        if (student == null) {
            return new JSONResult().fail(StudentErrorCodeEnum.STUDENT_NOT_FOUND.getCode(),
                    StudentErrorCodeEnum.STUDENT_NOT_FOUND.getMessage());
        }
        return redPocketLottery(student, liveRedPocketReqDTO);
    }


    /**
     * 红包抽奖
     *
     * @param student
     * @param liveRedPocketReqDTO
     * @return
     */
    private JSONResult<LiveRedPocketRecordDTO> redPocketLottery(PeStudent student, LiveRedPocketReqDTO liveRedPocketReqDTO) {

        JSONResult<LiveRedPocketRecordDTO> liveRedPocketRecordDTOJSONResult = new JSONResult<>();
        LiveRedPocketRecordDTO liveRedPocketRecordDTO = new LiveRedPocketRecordDTO();

        PeLiveRedPocketRecord liveRedPocketRecord = liveRedPocketRecordMapper.getByStuIdAndLiveRedPocketId(liveRedPocketReqDTO);
        if (liveRedPocketRecord != null) {
            return liveRedPocketRecordDTOJSONResult.fail(LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_HAVE_GET.getCode(),
                    LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_HAVE_GET.getMessage());
        }
        BigDecimal totalAmount = liveRedPocketRecordMapper.
                getTotalAmountByLiveRedPocketId(liveRedPocketReqDTO.getLiveRedPocketId());

        if (totalAmount == null) {
            totalAmount = BigDecimal.valueOf(0);
        }

        PeLiveRedPocketRule peLiveRedPocketRule = liveRedPocketRuleMapper.getById(liveRedPocketReqDTO.getLiveRedPocketId());
        //未找到红包
        if (peLiveRedPocketRule == null) {
            return liveRedPocketRecordDTOJSONResult.fail(LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_NOT_FOUND.getCode(),
                    LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_NOT_FOUND.getMessage());
        }
        //还未发送红包
        if (LiveRedPocketRuleEnum.SAVED.getCode() == peLiveRedPocketRule.getStatus()) {
            return liveRedPocketRecordDTOJSONResult.fail(LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_NOT_START.getCode(),
                    LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_NOT_START.getMessage());
        }
        //该红包已经结束
        if (LiveRedPocketRuleEnum.SAVED.getCode() == peLiveRedPocketRule.getStatus()) {
            return liveRedPocketRecordDTOJSONResult.fail(LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_END.getCode(),
                    LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_END.getMessage());
        }

        Random random = new Random();
        int max = peLiveRedPocketRule.getStandardAmount()
                .add(peLiveRedPocketRule.getFloatingAmount())
                .multiply(new BigDecimal(100)).intValue();
        int min = peLiveRedPocketRule.getStandardAmount()
                .subtract(peLiveRedPocketRule.getFloatingAmount())
                .multiply(new BigDecimal(100)).intValue();
        //最大值和最小值都为正整数
        if (!(max > 0 && min > 0 && max >= min)) {
            return liveRedPocketRecordDTOJSONResult.fail(LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_END.getCode(),
                    LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_END.getMessage());
        }
        int luckNo = random.nextInt(max) % (max - min + 1) + min;
        if (luckNo <= 0) {
            return liveRedPocketRecordDTOJSONResult.fail(LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_END.getCode(),
                    LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_END.getMessage());
        }
        int remain = peLiveRedPocketRule.getTotalAmount().subtract(totalAmount).multiply(new BigDecimal(100)).intValue();
        if (remain <= 0) {
            return liveRedPocketRecordDTOJSONResult.fail(LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_FINISH_OUT.getCode(),
                    LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_FINISH_OUT.getMessage());
        }

        PeLiveRedPocketRecord peLiveRedPocketRecord = new PeLiveRedPocketRecord();
        peLiveRedPocketRecord.setLiveRedPocketId(liveRedPocketReqDTO.getLiveRedPocketId());
        if (remain < luckNo) {
            peLiveRedPocketRecord.setAmount(new BigDecimal(remain).divide(new BigDecimal(100)));
        } else {
            peLiveRedPocketRecord.setAmount(new BigDecimal(luckNo).divide(new BigDecimal(100)));
        }
        peLiveRedPocketRecord.setCreateTime(new Date());
        peLiveRedPocketRecord.setUid(student.getId());
        int result = liveRedPocketRecordMapper.addRedPockrtRecord(peLiveRedPocketRecord);
        if (result <= 0) {
            return liveRedPocketRecordDTOJSONResult.fail(LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_FAIL.getCode(),
                    LiveRedPocketErrorCodeEnum.LIVE_RED_POCKET_RECORD_FAIL.getMessage());
        }

        updateStudentAccount(student, peLiveRedPocketRecord);
        BeanUtils.copyProperties(liveRedPocketRecordDTO, peLiveRedPocketRecord);
        liveRedPocketRecordDTO.setStudent(student);
        liveRedPocketRecordDTOJSONResult.success(liveRedPocketRecordDTO);
        return liveRedPocketRecordDTOJSONResult;
    }


    /**
     * 更新学生账户
     *
     * @param student
     * @param peLiveRedPocketRecord
     */
    private void updateStudentAccount(PeStudent student, PeLiveRedPocketRecord peLiveRedPocketRecord) {
        PeStudentAccount peStudentAccount = studentAccountMapper.getStudentAccount(student.getId());
        if (peStudentAccount == null) {//增加账户信息
            peStudentAccount = new PeStudentAccount();
            peStudentAccount.setBlance(peLiveRedPocketRecord.getAmount());
            peStudentAccount.setUid(student.getId());
            peStudentAccount.setCreateTime(new Date());
            studentAccountMapper.addStudentAccount(peStudentAccount);
        } else {//修改账户信息
            peStudentAccount.setUpdateTime(new Date());
            studentAccountMapper.updateStudentAccount(peStudentAccount, peLiveRedPocketRecord.getAmount());
        }
    }


    /**
     * 红包次数检查
     *
     * @param liveRedPocketReqDTO
     * @return
     */
    @Override
    public JSONResult<CheckOpenRespDTO> checkOpen(LiveRedPocketReqDTO liveRedPocketReqDTO) {
        JSONResult<CheckOpenRespDTO> jsonResult = new JSONResult<>();
        CheckOpenRespDTO checkOpenRespDTO = new CheckOpenRespDTO();
        PeStudent student = studentMapper.getById(liveRedPocketReqDTO.getStuId());
        if (student == null) {
            checkOpenRespDTO.setCheckOpen(false);
        }
        PeLiveRedPocketRule peLiveRedPocketRule = liveRedPocketRuleMapper.getGoingByTaskID(liveRedPocketReqDTO.getTaskId());
        //未找到进行中红包
        if (peLiveRedPocketRule == null) {
            checkOpenRespDTO.setCheckOpen(false);
        } else {
            //还未发送红包
            if (LiveRedPocketRuleEnum.SAVED.getCode() == peLiveRedPocketRule.getStatus()) {
                checkOpenRespDTO.setCheckOpen(false);
            }else {
                //该红包已经结束
                if (LiveRedPocketRuleEnum.END.getCode() == peLiveRedPocketRule.getStatus()) {
                    checkOpenRespDTO.setCheckOpen(false);
                }else {
                    BigDecimal totalAmount = liveRedPocketRecordMapper.
                            getTotalAmountByLiveRedPocketId(peLiveRedPocketRule.getId());

                    if (totalAmount == null) {
                        totalAmount = BigDecimal.valueOf(0);
                    }
                    int remain = peLiveRedPocketRule.getTotalAmount().subtract(totalAmount).multiply(new BigDecimal(100)).intValue();
                    if (remain <= 0) {
                        checkOpenRespDTO.setCheckOpen(false);
                    }else {
                        liveRedPocketReqDTO.setLiveRedPocketId(peLiveRedPocketRule.getId());
                        PeLiveRedPocketRecord peLiveRedPocketRecord = liveRedPocketRecordMapper.getByStuIdAndLiveRedPocketId(liveRedPocketReqDTO);
                        if (peLiveRedPocketRecord != null) {
                            checkOpenRespDTO.setCheckOpen(false);

                        } else {
                            PeLiveRedPocketSend peLiveRedPocketSend=liveRedPocketSendMapper.getByStuIdAndLiveRedPocketId(liveRedPocketReqDTO);
                            if (peLiveRedPocketSend!=null){
                                checkOpenRespDTO.setCheckOpen(false);
                            }else {
                                peLiveRedPocketSend=new PeLiveRedPocketSend();
                                peLiveRedPocketSend.setCreateTime(new Date());
                                peLiveRedPocketSend.setLiveRedPocketId(peLiveRedPocketRule.getId());
                                peLiveRedPocketSend.setUid(student.getId());
                                liveRedPocketSendMapper.addRedPockrtRecord(peLiveRedPocketSend);
                                checkOpenRespDTO.setCheckOpen(true);
                                checkOpenRespDTO.setLiveRedPocketId(peLiveRedPocketRule.getId());
                                checkOpenRespDTO.setCopywriting(peLiveRedPocketRule.getCopywriting() == null ? "" : peLiveRedPocketRule.getCopywriting());
                            }
                        }
                    }
                }

            }

        }

        return jsonResult.success(checkOpenRespDTO);
    }

    @Override
    public List<LiveRedPocketRecordRespDTO> redPocketRecordList(int liveRedPocketId) {
        List<LiveRedPocketRecordRespDTO> liveRedPocketRecordRespDTOList = Lists.newArrayList();
        List<PeLiveRedPocketRecord> liveRedPocketRecordList = liveRedPocketRecordMapper.listByLiveRedPocketId(liveRedPocketId);
        if(null != liveRedPocketRecordList && liveRedPocketRecordList.size() > 0 ){
            for(PeLiveRedPocketRecord liveRedPocketRecord : liveRedPocketRecordList){
                LiveRedPocketRecordRespDTO liveRedPocketRecordRespDTO = new LiveRedPocketRecordRespDTO();
                BeanUtils.copyProperties(liveRedPocketRecordRespDTO, liveRedPocketRecord);
                Integer stuId = liveRedPocketRecord.getUid();
                PeStudent student = studentMapper.getById(stuId);
                liveRedPocketRecordRespDTO.setStudent(student);
                liveRedPocketRecordRespDTOList.add(liveRedPocketRecordRespDTO);
            }
        }
        return liveRedPocketRecordRespDTOList;
    }

}
