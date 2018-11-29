package com.company.project.mapper;

import com.company.project.dto.AccountDto;
import com.company.project.dto.RoleDto;
import com.company.project.dto.UserDto;
import com.company.project.exception.MapperException;
import com.company.project.model.AccountEntity;
import com.company.project.model.RoleEntity;
import com.company.project.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class UserMapper implements AbstractMapper<UserEntity, UserDto> {
    private RoleMapper roleMapper;
    private AccountMapper accountMapper;

    @Autowired
    public UserMapper(RoleMapper roleMapper, AccountMapper accountMapper) {
        this.roleMapper = roleMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public void fromDtoToExistingEntity(UserDto userDto, UserEntity userEntity) {
        if (userDto == null || userEntity == null) {
            throw new MapperException("Unable to map from UserDto to existing UserEntity");
        }
        String name = userDto.getName();
        if (name != null)
            userEntity.setName(name);
        String surname = userDto.getSurname();
        if (surname != null)
            userEntity.setSurname(surname);
        String phone = userDto.getPhone();
        if (phone != null)
            userEntity.setPhone(phone);
        AccountDto accountDto = userDto.getAccount();
        AccountEntity existingAccountEntity = userEntity.getAccount();
        if (accountDto != null) {
            if (existingAccountEntity != null) {
                accountMapper.fromDtoToExistingEntity(accountDto, existingAccountEntity);
            } else {
                AccountEntity accountEntity = accountMapper.fromDtoToNewEntity(accountDto);
                accountEntity.addUser(userEntity);
            }
        }

        List<RoleDto> roleDtos = userDto.getRoles();
        Set<RoleEntity> roleEntities = userEntity.getRoles();
        if (roleDtos != null && !roleDtos.isEmpty()) {
            if (roleEntities.isEmpty()) {
                for (RoleDto roleDto : roleDtos) {
                    RoleEntity roleEntity = roleMapper.fromDtoToNewEntity(roleDto);
                    userEntity.addRole(roleEntity);
                }
            } else {
                for (RoleDto roleDto : roleDtos) {
                    RoleEntity.Role roleDtoValue = RoleEntity.Role.valueOf(roleDto.getRoleName());
                    if (!(ifRoleExistsInCollection(roleDtoValue, roleEntities))) {
                        RoleEntity roleEntity = roleMapper.fromDtoToNewEntity(roleDto);
                        userEntity.addRole(roleEntity);
                    }
                }

            }
        }

    }

    @Override
    public UserEntity fromDtoToNewEntity(UserDto dto) {
        if (dto == null) {
            throw new MapperException("Unable to map from UserDto to new UserEntity");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(dto.getName());
        userEntity.setSurname(dto.getSurname());
        userEntity.setPhone(dto.getPhone());
        AccountEntity accountEntity = accountMapper.fromDtoToNewEntity(dto.getAccount());
        accountEntity.addUser(userEntity);
        List<RoleDto> roleDtos = dto.getRoles();
        for (RoleDto roleDto : roleDtos) {
            RoleEntity roleEntity = roleMapper.fromDtoToNewEntity(roleDto);
            userEntity.addRole(roleEntity);
        }
        return userEntity;
    }

    @Override
    public UserDto fromEntityToNewDto(UserEntity userEntity) {
        if (userEntity == null) {
            throw new MapperException("Unable to map from UserEntity to UserDto");
        }
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setSurname(userEntity.getSurname());
        userDto.setPhone(userEntity.getPhone());
        AccountEntity accountEntity = userEntity.getAccount();
        AccountDto accountDto = accountMapper.fromEntityToNewDto(accountEntity);
        userDto.setAccount(accountDto);
        Set<RoleEntity> roleEntities = userEntity.getRoles();
        userDto.setRoles(new LinkedList<>());
        for (RoleEntity roleEntity : roleEntities) {
            RoleDto roleDto = roleMapper.fromEntityToNewDto(roleEntity);
            userDto.addRole(roleDto);
        }
        return userDto;
    }

    private boolean ifRoleExistsInCollection(RoleEntity.Role role, Set<RoleEntity> roleEntities) {
        for (RoleEntity roleEntity : roleEntities) {
            if (roleEntity.getRoleValue() == role)
                return true;
        }
        return false;
    }
}
