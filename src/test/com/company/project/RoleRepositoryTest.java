package com.company.project;

import com.company.project.model.RoleEntity;
import com.company.project.model.UserEntity;
import com.company.project.repository.RoleRepository;
import com.company.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class RoleRepositoryTest {
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    RoleRepositoryTest(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Test
    void findRolesOfThatUser() {
        RoleEntity firstRole = new RoleEntity();
        firstRole.setRoleValue(RoleEntity.Role.DEVELOPER);
        RoleEntity secondRole = new RoleEntity();
        secondRole.setRoleValue(RoleEntity.Role.MANAGER);
        RoleEntity thirdRole = new RoleEntity();
        thirdRole.setRoleValue(RoleEntity.Role.TESTER);
        UserEntity userEntity = new UserEntity();
        userEntity.addRole(firstRole);
        firstRole.setUser(userEntity);
        userEntity.addRole(secondRole);
        secondRole.setUser(userEntity);
        userEntity.addRole(thirdRole);
        thirdRole.setUser(userEntity);
        userRepository.save(userEntity);
        List<RoleEntity> rolesOfThatUser = roleRepository.findRoleEntitiesByUser(userEntity);
        assertAll(
                () -> assertTrue(rolesOfThatUser.contains(firstRole)),
                () -> assertTrue(rolesOfThatUser.contains(secondRole)),
                () -> assertTrue(rolesOfThatUser.contains(thirdRole))
        );
        userRepository.delete(userEntity);
    }

    @Test
    void uniqueRoleAndUserCombinationConstraint() {
        UserEntity userEntity = new UserEntity();
        RoleEntity firstSameRole = new RoleEntity();
        firstSameRole.setRoleValue(RoleEntity.Role.DEVELOPER);
        userEntity.addRole(firstSameRole);
        firstSameRole.setUser(userEntity);
        userRepository.save(userEntity);
        UserEntity sameUserEntityFromDb = userRepository.findAll().get(0);
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleValue(RoleEntity.Role.DEVELOPER);
        sameUserEntityFromDb.addRole(roleEntity);
        roleEntity.setUser(sameUserEntityFromDb);
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(sameUserEntityFromDb));
        userRepository.deleteAll();
    }

    @Test
    void findUsersWithRole() {
        UserEntity firstUser = new UserEntity();
        UserEntity secondUser = new UserEntity();
        UserEntity thirdUser = new UserEntity();
        RoleEntity firstDevRole = new RoleEntity();
        firstDevRole.setRoleValue(RoleEntity.Role.DEVELOPER);
        firstDevRole.setUser(firstUser);
        firstUser.addRole(firstDevRole);
        RoleEntity secondDevRole = new RoleEntity();
        secondDevRole.setRoleValue(RoleEntity.Role.DEVELOPER);
        secondDevRole.setUser(secondUser);
        secondUser.addRole(secondDevRole);
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        List<UserEntity> usersWithDevRole = userRepository.findAllByRole(RoleEntity.Role.DEVELOPER);
        assertAll(
                () -> assertTrue(usersWithDevRole.contains(firstUser)),
                () -> assertTrue(usersWithDevRole.contains(secondUser))
        );
        userRepository.deleteAll();
    }

    @Test
    void updateUser(){
        /*UserEntity userEntity = new UserEntity();
        userEntity.setName("Jan");
        userEntity.setSurname("Kowalski");
        userEntity.setPhone("123456789");
        userEntity.setBirthDate(LocalDate.now());
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setActive(true);
        accountEntity.setVerificationCode("code");
        accountEntity.setPassword("password");
        accountEntity.setEmail("jankowalski@gmail.com");
        userEntity.setAccount(accountEntity);
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleValue(RoleEntity.Role.DEVELOPER);
        userEntity.addRole(roleEntity);
        userRepository.save(userEntity);*/
        UserEntity userEntity = userRepository.findById(1L).get();
        userEntity.getAccount().setEmail("newemail@gmail.com");
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleValue(RoleEntity.Role.MANAGER);
        userEntity.addRole(roleEntity);
        userRepository.save(userEntity);


    }
}