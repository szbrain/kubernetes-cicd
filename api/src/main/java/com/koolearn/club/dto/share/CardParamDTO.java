package com.koolearn.club.dto.share;


import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardParamDTO extends BaseSerialization{
    private String nickName;
    private String avatarUrl;
    private int checkDays;
    private String checkTime;
    private String className;
    private int classMembers;
    private String QRCodeUrl;
    private String backgroundUrl;
}
