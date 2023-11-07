package com.cross.jp.projectmanage.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 顧客テーブルの情報
 */
@Entity
@Table(name = "client")
@Getter
@Setter
public class Client {
    @Id     //主キー
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自動生成
    private Integer id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "post_code",length = 20)
    private String postCode;

    @Column(name = "telephone_number",length = 20)
    private String telephoneNumber;

    @Column(name = "fax",length = 20)
    private String fax;

    @Column(name = "address",length = 100)
    private String address;

    @Column(name = "note",length = 200)
    private String note;

    @OneToMany(mappedBy = "client",orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orders;
}
