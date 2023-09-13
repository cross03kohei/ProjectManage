package com.cross.jp.projectmanage.service;

import com.cross.jp.projectmanage.dto.ProjectDto;
import com.cross.jp.projectmanage.repository.ClientRepository;
import com.cross.jp.projectmanage.repository.OrderRepository;
import com.cross.jp.projectmanage.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public void save(ProjectDto dto){

    }
}
