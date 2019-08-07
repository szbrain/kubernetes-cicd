package com.koolearn.club.dto.square;

import com.koolearn.club.entity.PeSquare;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/4/24.
 */
@Getter
@Setter
public class SquareListDTO extends PeSquare{
    private String teacher;
    private int classNum;
    private String className;
    private String classCover;

}
