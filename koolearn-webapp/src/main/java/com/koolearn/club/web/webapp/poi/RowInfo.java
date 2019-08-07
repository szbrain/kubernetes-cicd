package com.koolearn.club.web.webapp.poi;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
public class RowInfo extends ArrayList{
    int index;
    public RowInfo(int index) {
        this.index = index;
    }
}
