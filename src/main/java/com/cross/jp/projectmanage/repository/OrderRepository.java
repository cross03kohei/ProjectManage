package com.cross.jp.projectmanage.repository;

import com.cross.jp.projectmanage.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
