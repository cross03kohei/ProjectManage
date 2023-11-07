package com.cross.jp.projectmanage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgressDto {
    private Integer id;
    private Integer item;
    private Integer quantity;
    private Integer amount;
    private Integer manager;
    private Integer progress;
}
