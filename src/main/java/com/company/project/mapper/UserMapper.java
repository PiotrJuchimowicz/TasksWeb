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

import java.util.List;
import java.util.Set;

@Component
public class UserMapper implements AbstractMapper<UserEntity, UserDto> {
    private RoleMapper roleMapper;
    private AccountMapper accountMapper;

    @Autowired
    public UserMapper(RoleMapper roleMapper,AccountMapper accountMapper){
        this.roleMapper=roleMapper;
        this.accountMapper=accountMapper;
    }
    @Override
    public UserEntity fromDtoToEntity(UserDto userDto) {
        if(userDto==null){
            throw new MapperException("Unable to map from UserDto to UserEntity");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setBirthDate(userDto.getBirthDate());
        userEntity.setPhone(userDto.getPhone());
        AccountDto accountDto = userDto.getAccountDto();
        AccountEntity accountEntity = accountMapper.fromDtoToEntity(accountDto);
        userEntity.setAccount(accountEntity);
        Set<RoleDto> roleDtos = userDto.getRoleDtos();
        System.out.println(roleDtos);
        for(RoleDto roleDto:roleDtos){
            RoleEntity roleEntity = roleMapper.fromDtoToEntity(roleDto);
            userEntity.addRole(roleEntity);
        }
        return userEntity;
    }

    @Override
    public UserDto fromEntityToDto(UserEntity userEntity) {
        if(userEntity==null){
            throw new MapperException("Unable to map from UserEntity to UserDto");
        }
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setSurname(userEntity.getSurname());
        userDto.setBirthDate(userEntity.getBirthDate());
        userDto.setPhone(userEntity.getPhone());
        AccountEntity accountEntity = userEntity.getAccount();
        AccountDto accountDto = accountMapper.fromEntityToDto(accountEntity);
        userDto.setAccountDto(accountDto);
        Set<RoleEntity> roleEntities = userEntity.getRoles();
        for(RoleEntity roleEntity:roleEntities){
            RoleDto roleDto = roleMapper.fromEntityToDto(roleEntity);
            userDto.addRole(roleDto);
        }
        return userDto;
    }
}
