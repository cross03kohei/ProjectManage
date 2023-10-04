package com.cross.jp.projectmanage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProceedsDto {
    private Integer id;
    private Integer item;
    private Integer quantity;
    private Integer amount;
    @NotBlank
    private String deliveryDate;
}
