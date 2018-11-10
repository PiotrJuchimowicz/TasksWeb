package com.company.project.mapper;

import com.company.project.dto.AccountDto;
import com.company.project.exception.MapperException;
import com.company.project.model.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements AbstractMapper<AccountEntity, AccountDto> {
    @Override
    public AccountEntity fromDtoToEntity(AccountDto accountDto) {
        if(accountDto==null){
            throw new MapperException("Unable to map from AccountDto to AccountEntity");
        }
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountDto.getId());
        accountEntity.setEmail(accountDto.getEmail());
        accountEntity.setActive(accountDto.isActive());
        accountEntity.setPassword(accountDto.getPassword());
        accountEntity.setVerificationCode(accountDto.getVerificationCode());
        return accountEntity;
    }

    @Override
    public AccountDto fromEntityToDto(AccountEntity accountEntity) {
        if(accountEntity==null){
            throw new MapperException("Unable to map from AccountEntity to AccountDto");
        }
        AccountDto accountDto = new AccountDto();
        accountDto.setId(accountEntity.getId());
        accountDto.setEmail(accountEntity.getEmail());
        accountDto.setActive(accountEntity.isActive());
        accountDto.setPassword(accountEntity.getPassword());
        accountDto.setVerificationCode(accountEntity.getVerificationCode());
        return accountDto;
    }
}
