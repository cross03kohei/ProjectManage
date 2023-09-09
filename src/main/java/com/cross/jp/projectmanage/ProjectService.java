package com.cross.jp.projectmanage;

import com.cross.jp.projectmanage.repository.ClientRepository;
import com.cross.jp.projectmanage.repository.OrderRepository;
import com.cross.jp.projectmanage.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProjectRepository projectRepository;
}
