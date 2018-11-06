package com.company.project.repository;

import com.company.project.model.TaskEntity;
import com.company.project.model.UserEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TaskAndUserRepositoriesTest {
    UserRepository userRepository;
    TaskRepository taskRepository;

    @Autowired
    TaskAndUserRepositoriesTest(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Test
    void addingFromOwningSide() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setDescription("task description");
        taskEntity.setName("task name");
        taskEntity.setPriority(TaskEntity.Priority.HIGH);
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Piotr");
        userEntity.setSurname("Juchimowicz");
        userEntity.setBirthDate(LocalDate.now());
        userEntity.setPhone("12345678");
        taskEntity.addUser(userEntity);
       /* Set<TaskEntity> normalTasks = userEntity.getTasks();
        Set<UserEntity> normalUsers = taskEntity.getUsers();
        normalTasks.forEach((task)-> System.out.println(task.hashCode()));
        TaskEntity normalTask = normalTasks.iterator().next();
        System.out.println(normalTask);
        normalUsers.forEach((user)-> System.out.println(user.hashCode()));
        UserEntity normalUser = normalUsers.iterator().next();
        System.out.println(normalUser);*/
        taskRepository.save(taskEntity);
        assertAll(
                () -> assertFalse(userRepository.findAll().isEmpty()),
                () -> assertFalse(taskRepository.findAll().isEmpty())
        );
        /*Set<TaskEntity> proxyTasks = userEntity.getTasks();
        Set<UserEntity> proxyUsers = taskEntity.getUsers();
        proxyTasks.forEach((task)-> System.out.println(task.hashCode()));
        TaskEntity proxyTask = proxyTasks.iterator().next();
        System.out.println(proxyTask);
        proxyUsers.forEach((user)-> System.out.println(user.hashCode()));
        UserEntity proxyUser = proxyUsers.iterator().next();
        System.out.println(proxyUser);*/
        /*System.out.println("PROXY:");
        Set<UserEntity> proxyUsers = taskEntity.getUsers();
        proxyUsers.forEach((user)-> System.out.println(user.hashCode()));
        UserEntity proxyUser = proxyUsers.iterator().next();
        System.out.println(proxyUser);

        System.out.println("NORMAL:");
        System.out.println(userEntity.hashCode());
        System.out.println(userEntity);*/

        //mam ustawine eager: jak pobiore z bazy i zwraca eager collection jako Persistence Set i dziala - dziala
        //mam ustawione lazy: jak pobiore z bazy i  zwraca lazy collection - oczywisty lazy exception
        // jak zapisze ale dzialam na tym co zapisz ma Persistence Set - nie dziala - poczytaj o save w spring data

        TaskEntity taskFromDb = taskRepository.findAll().get(0);
        UserEntity userFromDb = userRepository.findAll().get(0);
        Set<UserEntity> users = taskFromDb.getUsers();
        System.out.println(users);
        System.out.println(userFromDb);
        System.out.println(taskFromDb.getUsers().remove(userFromDb));
        System.out.println(users);

        /*Set<UserEntity> normalSet = new LinkedHashSet<>();
        normalSet.add(taskEntity.getUsers().iterator().next());
        System.out.println(normalSet);
        System.out.println(normalSet.remove(userEntity));
        System.out.println(normalSet);
        taskRepository.delete(taskEntity);*/
    }
}
