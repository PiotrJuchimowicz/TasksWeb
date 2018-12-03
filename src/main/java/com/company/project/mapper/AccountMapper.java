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
        String email = dto.getEmail();
        if (email != null)
            entity.setEmail(email);
        Boolean isActive = dto.getIsActive();
        if (isActive != null)
            entity.setActive(isActive);
        String password = dto.getPassword();
        if (password != null) {
            int passwordHash = password.hashCode();
            entity.setPassword(String.valueOf(passwordHash));
        }
    }

    @Override
    public AccountEntity fromDtoToNewEntity(AccountDto dto) {
        if (dto == null) {
            throw new MapperException("Unable to map from AccountDto to new AccountEntity");
        }
        AccountEntity entity = new AccountEntity();
        entity.setEmail(dto.getEmail());
        String password = dto.getPassword();
        int passwordHash = password.hashCode();
        entity.setPassword(String.valueOf(passwordHash));
        entity.setPassword(String.valueOf(password));
        entity.setActive(dto.getIsActive());
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
        dto.setIsActive(accountEntity.isActive());
        dto.setPassword(accountEntity.getPassword());
        dto.setUserId(accountEntity.getUser().getId());
        return dto;
    }
}
