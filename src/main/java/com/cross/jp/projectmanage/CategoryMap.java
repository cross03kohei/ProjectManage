package com.cross.jp.projectmanage;

import java.util.HashMap;
import java.util.Map;
//カテゴリーをMapで管理
public class CategoryMap {
    public static Map<Integer,String> items = new HashMap<>(); //商品
    private static Map<Integer,String> manager = new HashMap<>();   //担当者
    private static Map<Integer,String> prohress = new HashMap<>();  //進歩状況
    static {
        items.put(0,"名刺");
        items.put(1,"封筒");
        items.put(2,"伝票");
        items.put(3,"チラシ");
        items.put(4,"印章");
        items.put(5,"はがき");
        items.put(6,"ステッカー・シール");
        items.put(7,"Tシャツ");
        items.put(8,"ホームページ");
        items.put(9,"その他");

        manager.put(0,"久保田恵明");
        manager.put(1,"久保田満穂");

        prohress.put(0,"未着手");
        prohress.put(1,"進行中");
        prohress.put(2,"完了");
    }
}
