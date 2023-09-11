package com.cross.jp.projectmanage.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDto {
    private Integer clientId;
    private Integer item;
    private Integer quantity;
    private Integer amount;
    private Integer manager;
    private String receptionDate;
    private Integer progress;
    private String delivery_date;
}
