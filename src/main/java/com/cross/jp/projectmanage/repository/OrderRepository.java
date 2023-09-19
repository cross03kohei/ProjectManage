package com.cross.jp.projectmanage.repository;

import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer>, JpaSpecificationExecutor<Order> {
    @Query("SELECT m FROM Order m WHERE m.id = :id")
    Order getByIdOrder(@Param("id")Integer id);
    @Query("SELECT m FROM Order m WHERE m.deliveryDate LIKE :date" +"% order by m.deliveryDate desc")
    List<Order> getByMouthOrder(@Param("date")String date);
}
