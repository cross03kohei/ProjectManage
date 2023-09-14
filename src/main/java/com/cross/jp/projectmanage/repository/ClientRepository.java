package com.cross.jp.projectmanage.repository;

import com.cross.jp.projectmanage.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    /**
     *idと一致する顧客の情報を習得する
     */
    @Query(value = "SELECT m FROM Client m WHERE m.id = :id")
    Client getByIdClient(@Param("id")Integer id);
    @Query("SELECT m FROM Client m WHERE m.name LIKE %:name" + "%")
    List<Client> getByNameClientList(@Param("name")String name);
}
