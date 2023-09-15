package com.cross.jp.projectmanage.repository;

import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query(value = "SELECT m FROM Order m WHERE m.id = :id")
    Order getByIdOrder(@Param("id")Integer id);
}
