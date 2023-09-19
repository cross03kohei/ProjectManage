package com.cross.jp.projectmanage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * orderテーブルの情報
 */
@Entity
@Table(name = "order_detail")
@Getter
@Setter
public class Order {
    @Id     //主キー
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自動生成
    private Integer id;

    @Column(name = "item_category")
    private Integer itemCategory;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private Integer amount;

    /**プロジェクト関係
     * 受付日
     */
    @Column(name = "reception_date",length = 10)
    private String receptionDate;

    /**
     * 納品日
     */
    @Column(name = "delivery_date", length = 10)
    private String deliveryDate;

    /**
     * 進歩状況(Mapで管理するためINT型)
     */
    @Column(name = "progress")
    private Integer progress;

    /**
     * 担当者(Mapで管理するためINT型)
     */
    @Column(name = "manager")
    private Integer manager;

    /**
     * 入金済みのチェック
     */
    @Column(name = "check_payment")
    private Boolean paymentCheck;

    /**
     * 納品チェック
     */
    @Column(name = "check_end")
    private Boolean endCheck;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;
}
