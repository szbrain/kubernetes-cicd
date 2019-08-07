package com.koolearn.club.web.webapp.poi;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SheetInfo {
    private String name;
    private RowInfo title;
    private List<RowInfo> data;



}
