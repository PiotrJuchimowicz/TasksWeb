package com.company.project.service_impl;


import com.company.project.model.UserEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl<UserEntity> implements UserService {
    @Autowired
    public UserServiceImpl(AbstractRepository<UserEntity> abstractRepository){
        super(abstractRepository);
    }
}
