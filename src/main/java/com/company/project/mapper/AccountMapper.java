package com.company.project.mapper;

import com.company.project.dto.AccountDto;
import com.company.project.exception.MapperException;
import com.company.project.model.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements AbstractMapper<AccountEntity, AccountDto> {
    @Override
    public void fromDtoToEntity(AccountDto accountDto,AccountEntity accountEntity) {
        if(accountDto==null || accountEntity==null){
            throw new MapperException("Unable to map from AccountDto to AccountEntity");
        }
        accountEntity.setEmail(accountDto.getEmail());
        accountEntity.setActive(accountDto.isActive());
        accountEntity.setPassword(accountDto.getPassword());
    }

    @Override
    public void fromEntityToDto(AccountEntity accountEntity,AccountDto accountDto) {
        if(accountEntity==null || accountDto==null){
            throw new MapperException("Unable to map from AccountEntity to AccountDto");
        }
        accountDto.setId(accountEntity.getId());
        accountDto.setEmail(accountEntity.getEmail());
        accountDto.setActive(accountEntity.isActive());
        accountDto.setPassword(accountEntity.getPassword());
    }
}
