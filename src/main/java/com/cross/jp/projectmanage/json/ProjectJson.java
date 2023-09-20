package com.cross.jp.projectmanage.json;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectJson {
    private Integer id;
    private String name;
    private Integer item;
    private Integer quantity;
    private Integer amount;
    private Integer manager;
}
