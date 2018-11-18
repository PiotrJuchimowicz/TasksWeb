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
    public void fromDtoToEntity(UserDto userDto,UserEntity userEntity) {
        if(userDto==null || userEntity==null){
            throw new MapperException("Unable to map from UserDto to UserEntity");
        }
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setBirthDate(userDto.getBirthDate());
        userEntity.setPhone(userDto.getPhone());
    }

    @Override
    public void fromEntityToDto(UserEntity userEntity,UserDto userDto) {
        if(userEntity==null || userDto==null){
            throw new MapperException("Unable to map from UserEntity to UserDto");
        }
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setSurname(userEntity.getSurname());
        userDto.setBirthDate(userEntity.getBirthDate());
        userDto.setPhone(userEntity.getPhone());
    }
}
