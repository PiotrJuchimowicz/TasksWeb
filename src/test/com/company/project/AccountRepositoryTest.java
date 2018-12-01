package com.company.project;

import com.company.project.model.AccountEntity;
import com.company.project.model.UserEntity;
import com.company.project.repository.AccountRepository;
import com.company.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccountRepositoryTest {
    UserRepository userRepository;
    AccountRepository accountRepository;
    String name = "Piotr";
    String surname = "Juchimowicz";
    String phone = "111 222 333";
    String email = "email@gmail.com";
    String password = "password";
    String verificationCode = "verification code";
    boolean isActive = true;

    @Autowired
    AccountRepositoryTest(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Test
    void removeAccounting() {
        UserEntity userEntity = new UserEntity();
        setUserProperties(userEntity);
        AccountEntity accountEntity = new AccountEntity();
        setAccountProperties(accountEntity);
        accountEntity.setUser(userEntity);
        userEntity.setAccount(accountEntity);
        userRepository.save(userEntity);
        userEntity.setAccount(null);
        accountRepository.delete(accountEntity);
    }

    @Test
    void addUser() {
        UserEntity userEntity = new UserEntity();
        setUserProperties(userEntity);
        userRepository.save(userEntity);
        List<UserEntity> resultFromDb = userRepository.findAllBySurname(surname);
        assertAll(
                () -> assertEquals(resultFromDb.get(0).getName(), name),
                () -> assertEquals(resultFromDb.get(0).getSurname(), surname),
                () -> assertEquals(resultFromDb.get(0).getPhone(), phone)
        );
        userRepository.delete(userEntity);
    }

    @Test
    void cascadeDeleteFromUser() {
        UserEntity userEntity = new UserEntity();
        setUserProperties(userEntity);
        AccountEntity accountEntity = new AccountEntity();
        setAccountProperties(accountEntity);
        userEntity.setAccount(accountEntity);
        accountEntity.setUser(userEntity);
        userRepository.save(userEntity);
        userRepository.delete(userEntity);
        assertAll(
                () -> assertTrue(accountRepository.findAll().isEmpty()),
                () -> assertTrue(userRepository.findAll().isEmpty())
        );
    }

    @Test
    void cascadeUpdateFromAccount() {
        UserEntity userEntity = new UserEntity();
        setUserProperties(userEntity);
        AccountEntity accountEntity = new AccountEntity();
        setAccountProperties(accountEntity);
        accountEntity.setUser(userEntity);
        userEntity.setAccount(accountEntity);
        accountRepository.save(accountEntity);
        accountEntity.setPassword("new password");
        userEntity.setName("new name");
        accountRepository.save(accountEntity);
        assertAll(
                () -> assertEquals(accountEntity.getPassword(), accountRepository.findByEmail(email).getPassword()),
                () -> assertEquals(userEntity.getName(), userRepository.findAllBySurname(surname).get(0).getName())
        );
        userRepository.delete(userEntity);
    }

    @Test
    void addAccountCascadingFromUser() {
        UserEntity userEntity = new UserEntity();
        setUserProperties(userEntity);
        AccountEntity accountEntity = new AccountEntity();
        setAccountProperties(accountEntity);
        userEntity.setAccount(accountEntity);
        accountEntity.setUser(userEntity);
        userRepository.save(userEntity);
        UserEntity userFromDb = userRepository.findByAccount(accountEntity);
        assertAll(
                () -> assertEquals(userFromDb.getAccount().isActive(), isActive),
                () -> assertEquals(userFromDb.getAccount().getEmail(), email),
                () -> assertEquals(userFromDb.getAccount().getPassword(), password)
        );
        userRepository.delete(userEntity);
    }

    @Test
    void addUserCascadingFromAccount() {
        UserEntity userEntity = new UserEntity();
        setUserProperties(userEntity);
        AccountEntity accountEntity = new AccountEntity();
        setAccountProperties(accountEntity);
        accountEntity.setUser(userEntity);
        userEntity.setAccount(accountEntity);
        accountRepository.save(accountEntity);
        assertAll(
                () -> assertEquals(accountEntity.getUser().getName(), name),
                () -> assertEquals(accountEntity.getUser().getSurname(), surname),
                () -> assertEquals(accountEntity.getUser().getPhone(), phone)
        );
        userRepository.delete(userEntity);
    }

    @Test
    void orphanRemoveAccount() {
        UserEntity userEntity = new UserEntity();
        setUserProperties(userEntity);
        AccountEntity accountEntity = new AccountEntity();
        setAccountProperties(accountEntity);
        userEntity.setAccount(accountEntity);
        userRepository.save(userEntity);
        UserEntity userFromDb = userRepository.findAllBySurname(surname).get(0);
        userFromDb.setAccount(null);
        userRepository.save(userFromDb);
        assertAll(
                () -> assertTrue(userRepository.findAll().isEmpty()),
                () -> assertTrue(accountRepository.findAll().isEmpty())
        );
    }

    @Test
    void findAccountByUser() {
        UserEntity userEntity = new UserEntity();
        setUserProperties(userEntity);
        AccountEntity accountEntity = new AccountEntity();
        setAccountProperties(accountEntity);
        userEntity.setAccount(accountEntity);
        accountEntity.setUser(userEntity);
        userRepository.save(userEntity);
        UserEntity userFromDb = userRepository.findAllBySurname(surname).get(0);
        AccountEntity accountOfUserFromDb = accountRepository.findByUser(userFromDb);
        assertEquals(userFromDb.getAccount(), accountOfUserFromDb);
        userRepository.delete(userEntity);
    }

    @Test
    void findUserByAccount() {
        UserEntity userEntity = new UserEntity();
        setUserProperties(userEntity);
        AccountEntity accountEntity = new AccountEntity();
        setAccountProperties(accountEntity);
        userEntity.setAccount(accountEntity);
        accountEntity.setUser(userEntity);
        userRepository.save(userEntity);
        accountEntity = accountRepository.findByEmail(email);
        userEntity = userRepository.findByAccount(accountEntity);
        assertEquals(accountEntity.getUser(), userEntity);
        userRepository.delete(userEntity);
    }

    @Test
    void uniqueEmail() {
        AccountEntity accountEntity = new AccountEntity();
        setAccountProperties(accountEntity);
        AccountEntity accountEntityWithSameEmail = new AccountEntity();
        setAccountProperties(accountEntityWithSameEmail);
        accountRepository.save(accountEntity);
        assertThrows(DataIntegrityViolationException.class, () -> accountRepository.save(accountEntityWithSameEmail));
        accountRepository.delete(accountEntity);
    }

    @Test
    void notNullEmail() {
        AccountEntity accountEntity = new AccountEntity();
        setAccountProperties(accountEntity);
        accountEntity.setEmail(null);
        assertThrows(DataIntegrityViolationException.class, () -> accountRepository.save(accountEntity));
    }

    @Test
    void findActiveAccounts(){
        AccountEntity firstAccountEntity = new AccountEntity();
        firstAccountEntity.setEmail("first@gmail.com");
        firstAccountEntity.setActive(true);
        accountRepository.save(firstAccountEntity);
        AccountEntity secondAccountEntity = new AccountEntity();
        secondAccountEntity.setEmail("second@gmail.com");
        secondAccountEntity.setActive(false);
        accountRepository.save(secondAccountEntity);
        AccountEntity thirdAccountEntity= new AccountEntity();
        thirdAccountEntity.setEmail("third@gmail.com");
        thirdAccountEntity.setActive(true);
        accountRepository.save(thirdAccountEntity);
        List<AccountEntity> activeAccountEntities= accountRepository.findAccountEntitiesByIsActive(true);
        assertEquals(2, activeAccountEntities.size());
        accountRepository.deleteAll();

    }

    private void setUserProperties(UserEntity userEntity) {
        if (userEntity == null)
            return;
        userEntity.setName(name);
        userEntity.setSurname(surname);
        userEntity.setPhone(phone);
    }

    private void setAccountProperties(AccountEntity accountEntity) {
        if (accountEntity == null)
            return;
        accountEntity.setEmail(email);
        accountEntity.setActive(isActive);
        accountEntity.setPassword(password);
    }
}