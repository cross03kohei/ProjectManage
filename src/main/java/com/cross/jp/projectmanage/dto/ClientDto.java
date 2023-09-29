package com.cross.jp.projectmanage.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private Integer id;
    @NotBlank
    private String name;
    @Pattern(regexp = "^([0-9]{7})?$")  //空文字かつ数字7桁を入力
    private String postCode;
    private String address;
    @Pattern(regexp = "^(0|\\d{9,10})?$") //空文字かつ数字が9桁か10桁
    private String telephoneNumber;
    @Pattern(regexp = "^(0|\\d{9,10})?$")
    private String fax;
    private String note;
}
