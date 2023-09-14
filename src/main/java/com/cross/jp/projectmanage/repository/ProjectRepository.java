package com.cross.jp.projectmanage.repository;

import com.cross.jp.projectmanage.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    @Query("SELECT m FROM Project m JOIN Client c ON m.client.id = :id")
    List<Project> getSaveProject(@Param("id")Integer id);
}
