package com.cross.jp.projectmanage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自動生成
    private Integer id;

    /**
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

    @Column(name = "check_payment")
    private Boolean checkPayment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    private List<Order> orders;
}
