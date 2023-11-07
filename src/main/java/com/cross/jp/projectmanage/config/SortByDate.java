package com.cross.jp.projectmanage.config;

import com.cross.jp.projectmanage.entity.Order;

import java.util.Comparator;

public class SortByDate implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDeliveryDate().compareTo(o2.getDeliveryDate());
    }
}
//Listを日付順にソートする