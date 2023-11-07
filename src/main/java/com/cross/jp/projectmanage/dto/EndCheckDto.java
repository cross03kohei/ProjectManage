package com.cross.jp.projectmanage.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndCheckDto {
    private Integer id;
    private Integer progress;
    private Boolean endCheck;
    @NotBlank
    private String date;

    @AssertTrue(message = "進歩状況を完了にしないと納品済みにできません")
    public boolean isEndCheckEmpty(){
        if(getProgress() != 2 && getEndCheck()){
            return false;
        }
        return true;
    }
}
