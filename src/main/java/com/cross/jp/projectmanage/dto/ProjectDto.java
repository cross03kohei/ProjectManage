package com.cross.jp.projectmanage.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class ProjectDto {
    private Integer id;
    private Integer clientId;
    private String clientName;
    private Integer item;
    private Integer quantity;
    private Integer amount;
    private Integer manager;
    private String receptionDate;
    private Integer progress;
    private String deliveryDate;

    /**
     *validationを使用 顧客IDか顧客名どちらかか入力されてたらtureを返す
     */
    @AssertTrue(message = "顧客を選択するか新たに顧客名を入力してください")
    public boolean isClientEmpty(){
        if(StringUtils.isEmpty(clientId) && StringUtils.isEmpty(clientName)) {
            return false;
        }
        return true;
    }
}
