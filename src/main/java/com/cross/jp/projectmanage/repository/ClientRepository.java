package com.cross.jp.projectmanage.repository;

import com.cross.jp.projectmanage.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    @Query(value = "SELECT m FROM Client m WHERE m.id = :id")
    Client getByIdClient(@Param("id")Integer id);
}
