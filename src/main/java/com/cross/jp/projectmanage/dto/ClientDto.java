package com.cross.jp.projectmanage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private Integer id;
    private String name;
    private String postCode;
    private String address;
    private String telephoneNumber;
    private String fax;
    private String note;
}
