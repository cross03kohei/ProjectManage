package com.cross.jp.projectmanage.repository;

import com.cross.jp.projectmanage.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
