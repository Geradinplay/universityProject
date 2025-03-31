package org.acme.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.enums.ItemsStatus;
import org.acme.exception.ItemsException;
import org.acme.model.User;

import java.util.List;


@Dependent
public class UserService {
    @Inject
    EntityManager manager;

    @Transactional
    public User createUser(User user) throws ItemsException {

        List<User> users = getAllUsers();// получаем список пользователей

        if (users.contains(user)) {// Проверяем на наличие этого предмета в базе данных
            throw new ItemsException(ItemsStatus.EXISTS.getLabel());
        }
        // Добавляем или обновляет существующий предмет
        return manager.merge(user);
    }

    public List<User> getAllUsers() {// Получаем всех пользователей
        List<User> users = manager.createNamedQuery(User.GET_ALL_ITEMS, User.class).getResultList();
        return users;
    }


    public User findUsersById(Long id) {
        User users = manager.createNamedQuery(User.FIND_USER_BY_ID, User.class).setParameter("id",id).getSingleResult();
        return users;
    }

    public User updateUser(User user){
        return manager.merge(user);
    }
}
