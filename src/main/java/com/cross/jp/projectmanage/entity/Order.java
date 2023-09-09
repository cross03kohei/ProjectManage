package com.cross.jp.projectmanage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * orderテーブルの情報
 */
@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自動生成される
    private Integer id;

    @Column(name = "item_category")
    private Integer itemCategory;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
