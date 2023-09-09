package com.cross.jp.projectmanage.repository;

import com.cross.jp.projectmanage.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
