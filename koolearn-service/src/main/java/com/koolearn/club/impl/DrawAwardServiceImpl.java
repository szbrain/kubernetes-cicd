package com.koolearn.club.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.constants.activity.ActivityErrorCodeEnum;
import com.koolearn.club.constants.activity.RedPocketErrorCodeEnum;
import com.koolearn.club.constants.award.AwardErrorCodeEnum;
import com.koolearn.club.constants.award.AwardRecordErrorCodeEnum;
import com.koolearn.club.dto.activity.ActivityDetailReqDTO;
import com.koolearn.club.dto.award.*;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.entity.*;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.*;
import com.koolearn.club.service.IDrawAwardService;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.util.BeanUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by lvyangjun on 2018/4/18.
 */
public class DrawAwardServiceImpl implements IDrawAwardService {

    // 放大倍数
    private static final int mulriple = 10000;

    @Resource
    DrawAwardItemMapper drawAwardItemMapper;

    @Resource
    DrawAwardMapper drawAwardMapper;

    @Resource
    DrawRecordMapper drawRecordMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private ClassMapper classMapper;

    @Resource
    private RedPocketRuleMapper redPocketRuleMapper;

    @Resource
    private RedPocketRecordMapper redPocketRecordMapper;

    @Resource
    private StudentAccountMapper studentAccountMapper;

    /**
     * 抽奖
     *
     * @param sid
     * @param activityId
     * @return
     */
    @Override
    @Transactional
    public JSONResult doDraw(String sid, int activityId, List<Integer> learingTypeList) {
        JSONResult jsonResult = new JSONResult();
        PeStudent student = studentMapper.getBySid(sid);
        ActivityDetailReqDTO activityDetailReqDTO = new ActivityDetailReqDTO();
        activityDetailReqDTO.setActivityId(activityId);
        PeActivity peActivity = activityMapper.activityDetail(activityDetailReqDTO);

        if (check(peActivity, student, learingTypeList)) {
            if (peActivity.getType() == 1) {
                jsonResult = turntableLottery(activityId, student);
            } else if (peActivity.getType() == 4) {
                jsonResult = redPocketLottery(activityId, student);
            }

        } else {
            jsonResult.fail(ActivityErrorCodeEnum.ACTIVITY_NO_ALLOWED.getCode(), ActivityErrorCodeEnum.ACTIVITY_NO_ALLOWED.getMessage());
        }
        return jsonResult;
    }

    /**
     * 红包抽奖
     *
     * @param activityId
     * @param student
     * @return
     */
    private JSONResult<RedPocketRecordDTO> redPocketLottery(int activityId, PeStudent student) {
        JSONResult<RedPocketRecordDTO> redPocketRecordDTOJSONResult = new JSONResult<>();
        RedPocketRecordDTO redPocketRecordDTO = new RedPocketRecordDTO();
        CheckAwardReqDTO checkAwardReqDTO = new CheckAwardReqDTO();
        checkAwardReqDTO.setStuId(student.getId());
        checkAwardReqDTO.setActivityId(activityId);
        BigDecimal everyDayAmount = redPocketRecordMapper.getEveryDayAmountByActivityId(activityId);
        if (everyDayAmount == null) {
            everyDayAmount = BigDecimal.valueOf(0);
        }
        PeRedPocketRule redPocketRule = redPocketRuleMapper.findByActivityId(activityId);
        Random random = new Random();
        int max = redPocketRule.getStandardAmount().add(redPocketRule.getFloatingAmount()).multiply(new BigDecimal(100)).intValue();
        int min = redPocketRule.getStandardAmount().subtract(redPocketRule.getFloatingAmount()).multiply(new BigDecimal(100)).intValue();
        ;
        int luckNo = random.nextInt(max) % (max - min + 1) + min;
        int remain = redPocketRule.getEveryDayAmount().subtract(everyDayAmount).multiply(new BigDecimal(100)).intValue();
        if (remain > 0) {
            PeRedPocketRecord peRedPocketRecord = new PeRedPocketRecord();
            peRedPocketRecord.setActivityId(activityId);
            if (remain < luckNo) {
                peRedPocketRecord.setAmount(new BigDecimal(remain).divide(new BigDecimal(100)));
            } else {
                peRedPocketRecord.setAmount(new BigDecimal(luckNo).divide(new BigDecimal(100)));
            }
            peRedPocketRecord.setCreateTime(new Date());
            peRedPocketRecord.setUid(student.getId());
            int result = redPocketRecordMapper.addRedPockrtRecord(peRedPocketRecord);
            if (result > 0) {
                updateStudentAccount(student, peRedPocketRecord);
                BeanUtils.copyProperties(redPocketRecordDTO, peRedPocketRecord);
                redPocketRecordDTO.setStudent(student);
                redPocketRecordDTOJSONResult.success(redPocketRecordDTO);
            } else {
                redPocketRecordDTOJSONResult.fail(RedPocketErrorCodeEnum.RED_POCKET_RECORD_FAIL.getCode(),
                        RedPocketErrorCodeEnum.RED_POCKET_RECORD_FAIL.getMessage());
            }

        } else {
            redPocketRecordDTOJSONResult.fail(RedPocketErrorCodeEnum.RED_POCKET_FINISH_OUT.getCode(),
                    RedPocketErrorCodeEnum.RED_POCKET_FINISH_OUT.getMessage());
        }
        return redPocketRecordDTOJSONResult;
    }


    /**
     * 更新学生账户
     *
     * @param student
     * @param peRedPocketRecord
     */
    private void updateStudentAccount(PeStudent student, PeRedPocketRecord peRedPocketRecord) {

        PeStudentAccount peStudentAccount = studentAccountMapper.getStudentAccount(student.getId());

        if (peStudentAccount == null) {//增加账户信息
            peStudentAccount = new PeStudentAccount();
            peStudentAccount.setBlance(peRedPocketRecord.getAmount());
            peStudentAccount.setUid(student.getId());
            peStudentAccount.setCreateTime(new Date());
            studentAccountMapper.addStudentAccount(peStudentAccount);
        } else {//修改账户信息
            peStudentAccount.setUpdateTime(new Date());
            studentAccountMapper.updateStudentAccount(peStudentAccount, peRedPocketRecord.getAmount());
        }
    }

    /**
     * 转盘抽奖
     *
     * @param activityId
     * @param student
     * @return
     */
    private JSONResult<DrawAwardRecordDTO> turntableLottery(int activityId, PeStudent student) {

        DrawAwardRecordDTO drawAwardRecordDTO = new DrawAwardRecordDTO();
        List<PeDrawAwardItem> awardItems = drawAwardItemMapper.queryAwardItemByActivityId(activityId);
        PeDrawAwardItem drawAwardItem = doPlay(awardItems);
        if (!"未中奖".equals(drawAwardItem.getItemName())) {//获奖

            //学生抽中该奖项的总次数
            int stuTotalNum = drawRecordMapper.totalNumByStuIdAndItemId(student.getId(), drawAwardItem.getId());

            //今天某奖项被抽中的次数
            int todayTotalNum = drawRecordMapper.totalNumByItemId(drawAwardItem.getId());
            if (stuTotalNum >= drawAwardItem.getUserTotalNum() || todayTotalNum >= drawAwardItem.getDayTotalNum()) {//没有库存
                for (PeDrawAwardItem peDrawAwardItem : awardItems) {
                    if ("未中奖".equals(peDrawAwardItem.getItemName())) {
                        drawAwardItem = peDrawAwardItem;
                    }
                }
            } else {
                //更新库存
                int result = updateAwardItemTotalNum(drawAwardItem);
                if (result == 0) {
                    for (PeDrawAwardItem peDrawAwardItem : awardItems) {
                        if ("未中奖".equals(peDrawAwardItem.getItemName())) {
                            drawAwardItem = peDrawAwardItem;
                        }
                    }
                }
            }
        }
        PeDrawAward peDrawAward = drawAwardMapper.queryAwardById(drawAwardItem.getAwardId());
        PeDrawRecord peDrawRecord = new PeDrawRecord();
        peDrawRecord.setUid(student.getId());
        peDrawRecord.setActivityId(activityId);
        peDrawRecord.setAwardName(peDrawAward == null ? "未中奖" : peDrawAward.getAwardName());
        peDrawRecord.setAwardItemName(drawAwardItem.getItemName());
        peDrawRecord.setAwardItemId(drawAwardItem.getId());
        peDrawRecord.setCreateTime(new Date());
        peDrawRecord.setLevel(drawAwardItem.getLevel() == null ? 0 : drawAwardItem.getLevel());
        drawRecordMapper.addActDrawRecord(peDrawRecord);
        drawAwardMapper.updateGrantCount(drawAwardItem.getAwardId());
        BeanUtils.copyProperties(drawAwardRecordDTO, peDrawRecord);
        drawAwardRecordDTO.setStudent(student);
        return new JSONResult<DrawAwardRecordDTO>().success(drawAwardRecordDTO);

    }

    /**
     * 抽奖操作
     *
     * @param awardItems
     * @return
     */
    private PeDrawAwardItem doPlay(List<PeDrawAwardItem> awardItems) {
        PeDrawAwardItem awardItem = null;
        if (awardItems.isEmpty()) {
            throw new ClubServiceException(SystemErrorCode.INVALID_DATA);
        }
        int lastScope = 0;
        Collections.shuffle(awardItems);
        Map<Integer, int[]> awardItemScope = new HashMap();
        for (PeDrawAwardItem item : awardItems) { //item.getProbability=0.05 = 5%
            int currentScope = lastScope + new BigDecimal(item.getProbability().floatValue()).multiply(new BigDecimal(mulriple)).intValue();
            awardItemScope.put(item.getId(), new int[]{lastScope + 1, currentScope});
            lastScope = currentScope;
        }
        int luckyNumber = new Random().nextInt(mulriple);
        int luckyPrizeId = 0;
        if (!awardItemScope.isEmpty()) {
            Set<Map.Entry<Integer, int[]>> set = awardItemScope.entrySet();
            for (Map.Entry<Integer, int[]> entry : set)
                if (luckyNumber >= entry.getValue()[0] && luckyNumber <= entry.getValue()[1]) {
                    luckyPrizeId = entry.getKey();
                    break;
                }
        }
        for (PeDrawAwardItem item : awardItems) {
            if (item.getId().intValue() == luckyPrizeId) {
                awardItem = item;
                break;
            }
        }
        return awardItem;
    }


    /**
     * 抽奖检查
     *
     * @param peActivity
     * @param peStudent
     * @param learingTypeList
     * @return
     */
    private boolean check(PeActivity peActivity, PeStudent peStudent, List<Integer> learingTypeList) {
        boolean result = false;
        if (peActivity != null
                && peActivity.getStatus() == 1) {
            CheckAwardReqDTO checkAwardReqDTO = new CheckAwardReqDTO();
            checkAwardReqDTO.setStuId(peStudent.getId());
            checkAwardReqDTO.setActivityId(peActivity.getId());
            int count = 0;
            if (peActivity.getType() == 1) {
                List<PeDrawRecord> peDrawRecordList = drawRecordMapper.getPeDrawRecordListByUserId(checkAwardReqDTO);
                count = peDrawRecordList.size();
            } else if (peActivity.getType() == 4) {
                List<PeRedPocketRecord> redPocketRecordList = redPocketRecordMapper.getListByUserId(checkAwardReqDTO);
                count = redPocketRecordList.size();
            }
            RuleDTO ruleDTO = JSON.parseObject(peActivity.getRule(), RuleDTO.class);
            if (ruleDTO.getEveryDaylimit() == null || count < ruleDTO.getEveryDaylimit()) {
                DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
                DateTime dateTime = new DateTime();
                String nowStr = dateTime.toString("yyyy-MM-dd HH:mm");
                String dateStr = dateTime.toString("yyyy-MM-dd");
                long ruleStartTime = DateTime.parse(dateStr + " " + ruleDTO.getStartTime(), format).getMillis();
                long ruleEndTime = DateTime.parse(dateStr + " " + ruleDTO.getEndTime(), format).getMillis();
                long nowTime = DateTime.parse(nowStr, format).getMillis();
                if (nowTime < ruleEndTime && nowTime > ruleStartTime) {
                    if (learingTypeList != null && learingTypeList.size() > 0) {
                        if (ruleDTO.getType() == 0 || (ruleDTO.getType() != 0 && learingTypeList.contains(Integer.parseInt(ruleDTO.getType() + "")))) {
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 更新库存
     *
     * @param drawAwardItem
     */
    private int updateAwardItemTotalNum(PeDrawAwardItem drawAwardItem) {
        int result = drawAwardItemMapper.updateAwardItemTotalNum(drawAwardItem);
        return result;
    }

    /**
     * 抽奖次数检查
     *
     * @param checkAwardReqDTO
     * @return
     */
    @Override
    public JSONResult<CheckAwardRespDTO> checkAward(CheckAwardReqDTO checkAwardReqDTO) {
        CheckAwardRespDTO checkAwardRespDTO = new CheckAwardRespDTO();
        PeStudent student = studentMapper.getBySid(checkAwardReqDTO.getSid());
        checkAwardReqDTO.setStuId(student.getId());
        checkAwardReqDTO.setNow(new Date());
        PeActivity activity = activityMapper.getActivityByClassIdAndTriggerRule(checkAwardReqDTO);
        if (activity != null) {
            checkAwardReqDTO.setActivityId(activity.getId());
            int count = 0;
            if (activity.getType() == 1) {
                List<PeDrawRecord> peDrawRecordList = drawRecordMapper.getPeDrawRecordListByUserId(checkAwardReqDTO);
                count = peDrawRecordList.size();
            } else if (activity.getType() == 4) {
                count = 0;
            }
            RuleDTO ruleDTO = JSON.parseObject(activity.getRule(), RuleDTO.class);
            if (ruleDTO.getEveryDaylimit() != null && count >= ruleDTO.getEveryDaylimit()) {
                checkAwardRespDTO.setCheckAward(false);
            } else {
                DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
                DateTime dateTime = new DateTime();
                String nowStr = dateTime.toString("yyyy-MM-dd HH:mm");
                String dateStr = dateTime.toString("yyyy-MM-dd");
                long ruleStartTime = DateTime.parse(dateStr + " " + ruleDTO.getStartTime(), format).getMillis();
                long ruleEndTime = DateTime.parse(dateStr + " " + ruleDTO.getEndTime(), format).getMillis();
                long nowTime = DateTime.parse(nowStr, format).getMillis();
                if (nowTime < ruleEndTime && nowTime > ruleStartTime) {
                    if (checkAwardReqDTO.getLearingTypeList() != null && checkAwardReqDTO.getLearingTypeList().size() > 0) {
                        if (ruleDTO.getType() == 0 || (ruleDTO.getType() != 0 && checkAwardReqDTO.getLearingTypeList().contains(Integer.parseInt(ruleDTO.getType() + "")))) {
                            checkAwardRespDTO.setCheckAward(true);
                            if (activity.getType() == 1) {
                                List<DrawAwardItemRespDTO> drawAwardItemRespDTOList = new ArrayList<>();
                                List<PeDrawAwardItem> peDrawAwardItemList = drawAwardItemMapper.queryAwardItemByActivityId(activity.getId());
                                if (peDrawAwardItemList.size() > 0) {
                                    for (PeDrawAwardItem peDrawAwardItem : peDrawAwardItemList) {
                                        DrawAwardItemRespDTO drawAwardItemRespDTO = new DrawAwardItemRespDTO();
                                        BeanUtils.copyProperties(drawAwardItemRespDTO, peDrawAwardItem);
                                        PeDrawAward peDrawAward = drawAwardMapper.queryAwardById(peDrawAwardItem.getAwardId());

                                        AwardRespDTO awardRespDTO = new AwardRespDTO();
                                        if (peDrawAward != null) {
                                            BeanUtils.copyProperties(awardRespDTO, peDrawAward);
                                        } else {
                                            awardRespDTO.setAwardName("未中奖");
                                            awardRespDTO.setAwardInfo("未中奖");
                                            awardRespDTO.setAwardType(1);
                                            awardRespDTO.setImgUrl("http://kooclub-1252392743.picsh.myqcloud.com/086d63ccbbd84206b266f256ae62dbdf.png");
                                        }
                                        drawAwardItemRespDTO.setAwardRespDTO(awardRespDTO);
                                        drawAwardItemRespDTOList.add(drawAwardItemRespDTO);
                                    }
                                    checkAwardRespDTO.setDrawAwardItemRespDTOList(drawAwardItemRespDTOList);
                                }
                            }

                        } else {
                            checkAwardRespDTO.setCheckAward(false);
                        }
                    } else {
                        checkAwardRespDTO.setCheckAward(false);
                    }

                } else {
                    checkAwardRespDTO.setCheckAward(false);
                }
            }
            checkAwardRespDTO.setActivityId(activity.getId());
            checkAwardRespDTO.setSlogan(activity.getSlogan() == null ? "" : activity.getSlogan());
            checkAwardRespDTO.setTriggerRule(activity.getTriggerRule());
            return new JSONResult<CheckAwardRespDTO>().success(checkAwardRespDTO);
        } else {
            return new JSONResult<CheckAwardRespDTO>().fail(ActivityErrorCodeEnum.ACTIVITY_NOT_FOUND.getCode(), ActivityErrorCodeEnum.ACTIVITY_NOT_FOUND.getMessage());
        }
    }

    /**
     * 创建奖品
     *
     * @param awardReqDTO
     * @return
     */
    @Override
    public JSONResult addAward(AwardReqDTO awardReqDTO) {
        PeDrawAward drawAward = new PeDrawAward();
        BeanUtils.copyProperties(drawAward, awardReqDTO);
        drawAward.setStatus((short) 1);
        drawAward.setCreateTime(new Date());
        drawAward.setGrantCount(0);
        int id = drawAwardMapper.addAward(drawAward);
        if (id > 0) {
            return new JSONResult().success(id);
        } else {
            return new JSONResult().fail(AwardErrorCodeEnum.AWARD_SAVE_FAIL.getCode(), AwardErrorCodeEnum.AWARD_SAVE_FAIL.getMessage());
        }
    }

    /**
     * 编辑奖品
     *
     * @param awardEditReqDTO
     * @return
     */
    @Override
    public JSONResult editAward(AwardEditReqDTO awardEditReqDTO) {

        int id = drawAwardMapper.editAward(awardEditReqDTO);
        if (id > 0) {
            return new JSONResult().success(id);
        } else {
            return new JSONResult().fail(AwardErrorCodeEnum.AWARD_SAVE_FAIL.getCode(), AwardErrorCodeEnum.AWARD_SAVE_FAIL.getMessage());
        }
    }

    /**
     * 创建奖项
     *
     * @param drawAwardItemReqDTOList
     * @return
     */
    @Override
    public boolean addAwardItem(List<DrawAwardItemReqDTO> drawAwardItemReqDTOList, Integer activityId) {
        if (drawAwardItemReqDTOList != null && drawAwardItemReqDTOList.size() > 0) {

            float probability = 0;
            boolean isExistDefaul = false;
            for (DrawAwardItemReqDTO drawAwardItemReqDTO : drawAwardItemReqDTOList) {
                probability += drawAwardItemReqDTO.getProbability();
            }
            if (probability > 1) {
                throw new ClubServiceException(SystemErrorCode.BIZ_AWAER_ITEM_PARAM_PROBABILITY_ERROR);
            }
            if (probability <= 1) {
                DrawAwardItemReqDTO drawAwardItemReqDTO = new DrawAwardItemReqDTO();
                drawAwardItemReqDTO.setAwardId(0);//奖品ID设为0代表未中奖
                drawAwardItemReqDTO.setDayTotalNum(0);
                drawAwardItemReqDTO.setItemName("未中奖");
                drawAwardItemReqDTO.setProbability(1 - probability);
                drawAwardItemReqDTO.setUserTotalNum(0);
                drawAwardItemReqDTO.setTotalNum(0);
                drawAwardItemReqDTOList.add(drawAwardItemReqDTO);
            }
            for (DrawAwardItemReqDTO drawAwardItemReqDTO : drawAwardItemReqDTOList) {
                PeDrawAwardItem peDrawAwardItem = new PeDrawAwardItem();
                BeanUtils.copyProperties(peDrawAwardItem, drawAwardItemReqDTO);
                peDrawAwardItem.setActivityId(activityId);
                peDrawAwardItem.setEveryUserDayTotalNum(1);
                peDrawAwardItem.setCreateTime(new Date());
                peDrawAwardItem.setStatus(1);
                drawAwardItemMapper.addAwardItem(peDrawAwardItem);
            }
            return true;
        }
        return false;
    }

    /**
     * 编辑奖项
     *
     * @param drawAwardItemReqDTOList
     * @return
     */
    @Override
    public boolean editAwardItem(List<DrawAwardItemReqDTO> drawAwardItemReqDTOList, Integer activityId) {
        float probability = 0;
        if (drawAwardItemReqDTOList != null && drawAwardItemReqDTOList.size() > 0) {
            for (DrawAwardItemReqDTO drawAwardItemReqDTO : drawAwardItemReqDTOList) {
                probability += drawAwardItemReqDTO.getProbability();
            }
        }
        if (probability > 1) {
            throw new ClubServiceException(SystemErrorCode.BIZ_AWAER_ITEM_PARAM_PROBABILITY_ERROR);
        }
        List<PeDrawAwardItem> awardItems = drawAwardItemMapper.queryAwardItemByActivityId(activityId);
        if (drawAwardItemReqDTOList != null && drawAwardItemReqDTOList.size() > 0) {
            for (DrawAwardItemReqDTO drawAwardItemReqDTO : drawAwardItemReqDTOList) {
                if (drawAwardItemReqDTO.getId() != null && drawAwardItemReqDTO.getId() != 0) {
                    for (PeDrawAwardItem peDrawAwardItem : awardItems) {
                        if (peDrawAwardItem.getId() == drawAwardItemReqDTO.getId()) {
                            BeanUtils.copyProperties(peDrawAwardItem, drawAwardItemReqDTO);
                            drawAwardItemMapper.editAwardItem(peDrawAwardItem);
                        }
                    }
                } else {
                    PeDrawAwardItem peDrawAwardItem = new PeDrawAwardItem();
                    BeanUtils.copyProperties(peDrawAwardItem, drawAwardItemReqDTO);
                    peDrawAwardItem.setActivityId(activityId);
                    peDrawAwardItem.setEveryUserDayTotalNum(1);
                    peDrawAwardItem.setCreateTime(new Date());
                    peDrawAwardItem.setStatus(1);
                    drawAwardItemMapper.addAwardItem(peDrawAwardItem);
                }

            }
            //更新未中奖概率
            for (PeDrawAwardItem peDrawAwardItem : awardItems) {
                if ("未中奖".equals(peDrawAwardItem.getItemName())) {
                    peDrawAwardItem.setProbability(1 - probability);
                    drawAwardItemMapper.editAwardItem(peDrawAwardItem);
                }
            }

            return true;
        }
        return false;
    }

    @Override
    public List<DrawAwardItemRespDTO> getListByActivityId(int activityId) {
        List<DrawAwardItemRespDTO> drawAwardItemRespDTOList = new ArrayList<>();
        List<PeDrawAwardItem> peDrawAwardItemList = drawAwardItemMapper.queryAwardItemNoDefaulByActivityId(activityId);
        if (peDrawAwardItemList.size() > 0) {
            for (PeDrawAwardItem peDrawAwardItem : peDrawAwardItemList) {
                DrawAwardItemRespDTO drawAwardItemRespDTO = new DrawAwardItemRespDTO();
                BeanUtils.copyProperties(drawAwardItemRespDTO, peDrawAwardItem);
                PeDrawAward peDrawAward = drawAwardMapper.queryAwardById(peDrawAwardItem.getAwardId());
                if (null != peDrawAward) {
                    AwardRespDTO awardRespDTO = new AwardRespDTO();
                    BeanUtils.copyProperties(awardRespDTO, peDrawAward);
                    drawAwardItemRespDTO.setAwardRespDTO(awardRespDTO);
                    drawAwardItemRespDTOList.add(drawAwardItemRespDTO);
                }
            }
        }
        return drawAwardItemRespDTOList;
    }


    @Override
    public JSONPageResult<List<AwardListRespDTO>> getAwardListByTeach(AwardListReqDTO awardListReqDTO) {

        JSONPageResult<List<AwardListRespDTO>> listJSONPageResult = new JSONPageResult<List<AwardListRespDTO>>();
        List<PeDrawAward> peDrawAwardList = drawAwardMapper.getAwardListByTeach(awardListReqDTO);
        if (peDrawAwardList != null && peDrawAwardList.size() > 0) {
            List<AwardListRespDTO> awardListRespDTOList = new ArrayList<>();
            for (PeDrawAward peDrawAward : peDrawAwardList) {
                AwardListRespDTO awardListRespDTO = new AwardListRespDTO();
                BeanUtils.copyProperties(awardListRespDTO, peDrawAward);
                awardListRespDTOList.add(awardListRespDTO);
            }
            listJSONPageResult.success(awardListRespDTOList);
        } else {
            listJSONPageResult.success(new ArrayList<AwardListRespDTO>());
        }
        listJSONPageResult.setPageSize(awardListReqDTO.getPageSize());
        listJSONPageResult.setPageNum(awardListReqDTO.getOffset());
        int count = drawAwardMapper.getAwardListCountByTeach(awardListReqDTO);
        if (count % awardListReqDTO.getPageSize() == 0) {
            listJSONPageResult.setPages(count / awardListReqDTO.getPageSize());
        } else {
            listJSONPageResult.setPages((count / awardListReqDTO.getPageSize()) + 1);
        }
        listJSONPageResult.setTotal(count);
        return listJSONPageResult;
    }

    @Override
    public JSONResult updateAwardRecord(UpdateAwardRecordReqDTO updateAwardReqDTO) {
        JSONResult jsonResult = new JSONResult();
        PeDrawRecord peDrawRecord = drawRecordMapper.getPeDrawRecordById(updateAwardReqDTO);
        if (peDrawRecord != null) {
            int result = drawRecordMapper.updateAwardRecord(updateAwardReqDTO);
            if (result > 0) {
                jsonResult.success(null);
            } else {
                jsonResult.fail(AwardRecordErrorCodeEnum.AWARD_RECORD_SAVE_FAIL.getCode(),
                        AwardRecordErrorCodeEnum.AWARD_RECORD_SAVE_FAIL.getMessage());
            }
        } else {
            jsonResult.fail(AwardRecordErrorCodeEnum.AWARD_RECORD_NOT_FOUND.getCode(),
                    AwardRecordErrorCodeEnum.AWARD_RECORD_NOT_FOUND.getMessage());
        }

        return jsonResult;
    }

    @Override
    public PageDTO<AppAwardRecordListRespDTO> awardRecordList(AppAwardRecordListReqDTO appAwardRecordListReqDTO) {
        //初始化返回对象
        PageDTO<AppAwardRecordListRespDTO> pageDTO = new PageDTO<>();
        List<AppAwardRecordListRespDTO> appAwardRecordListRespDTOList = Lists.newArrayList();
        PeStudent student = studentMapper.getBySid(appAwardRecordListReqDTO.getSid());
        appAwardRecordListReqDTO.setStuId(student.getId());
        //查询学生加入的班级列表
        List<PeDrawRecord> peDrawRecordList = drawRecordMapper.awardRecordList(appAwardRecordListReqDTO);

        for (PeDrawRecord peDrawRecord : peDrawRecordList) {

            AppAwardRecordListRespDTO appAwardRecordListRespDTO = new AppAwardRecordListRespDTO();
            BeanUtils.copyProperties(appAwardRecordListRespDTO, peDrawRecord);
            appAwardRecordListRespDTO.setNickname(student.getNickname());
            PeActivity peActivity = activityMapper.getById(peDrawRecord.getActivityId());
            if (peActivity != null) {
                PeClass peClass = classMapper.getById(Integer.parseInt(peActivity.getClassId()));
                appAwardRecordListRespDTO.setClassId(peClass.getId());
                appAwardRecordListRespDTO.setClassName(peClass.getName());
            }
            appAwardRecordListRespDTOList.add(appAwardRecordListRespDTO);
        }
        pageDTO.setList(appAwardRecordListRespDTOList);
        pageDTO.setCount(drawRecordMapper.awardRecordListCount(appAwardRecordListReqDTO));
        return pageDTO;
    }

    @Override
    public JSONResult<List<AwardListRespDTO>> simpleActivityList(Integer teachId) {
        JSONResult<List<AwardListRespDTO>> jsonResult = new JSONResult<>();
        List<PeDrawAward> peDrawAwardList = drawAwardMapper.simpleActivityList(teachId);
        if (peDrawAwardList != null && peDrawAwardList.size() > 0) {
            List<AwardListRespDTO> awardListRespDTOList = new ArrayList<>();
            for (PeDrawAward peDrawAward : peDrawAwardList) {
                AwardListRespDTO awardListRespDTO = new AwardListRespDTO();
                BeanUtils.copyProperties(awardListRespDTO, peDrawAward);
                awardListRespDTOList.add(awardListRespDTO);
            }
            jsonResult.success(awardListRespDTOList);
        } else {
            jsonResult.success(new ArrayList<AwardListRespDTO>());
        }
        return jsonResult;
    }
}
