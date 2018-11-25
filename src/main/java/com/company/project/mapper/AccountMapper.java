package com.company.project.mapper;

import com.company.project.dto.AccountDto;
import com.company.project.exception.MapperException;
import com.company.project.model.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements AbstractMapper<AccountEntity, AccountDto> {

    @Override
    public void fromDtoToExistingEntity(AccountDto dto, AccountEntity entity) {
        if (dto == null || entity == null) {
            throw new MapperException("Unable to map from AccountDto to existing AccountEntity");
        }
        if (dto.getId() != null)
            entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setActive(dto.isActive());
        entity.setPassword(dto.getPassword());
    }

    @Override
    public AccountEntity fromDtoToNewEntity(AccountDto dto) {
        if (dto == null) {
            throw new MapperException("Unable to map from AccountDto to new AccountEntity");
        }
        AccountEntity entity = new AccountEntity();
        if (dto.getId() != null)
            entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setActive(dto.isActive());
        return entity;
    }

    @Override
    public AccountDto fromEntityToNewDto(AccountEntity accountEntity) {
        if (accountEntity == null) {
            throw new MapperException("Unable to map from AccountEntity to AccountDto");
        }
        AccountDto dto = new AccountDto();
        dto.setId(accountEntity.getId());
        dto.setEmail(accountEntity.getEmail());
        dto.setActive(accountEntity.isActive());
        dto.setPassword(accountEntity.getPassword());
        dto.setUserId(accountEntity.getUser().getId());
        return dto;
    }
}
