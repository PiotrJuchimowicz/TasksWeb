package com.company.project.service_impl;


import com.company.project.model.TableEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl extends AbstractServiceImpl<TableEntity> implements TableService {
    @Autowired
    public TableServiceImpl(AbstractRepository<TableEntity> abstractRepository) {
        super(abstractRepository);
    }
}
