package org.acme.service;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.acme.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(defaultAnswer = Answers.RETURNS_DEFAULTS)
public class UserServiceTest {

    @Mock
    EntityManager manager;

    @Inject
    UserService userService;

    @Test
    public void getAllUsersTest() {
        // Arrange
        User user1 = new User();
        User user2 = new User();
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(user1);
        mockUsers.add(user2);

        @SuppressWarnings("unchecked")
        TypedQuery<User> typedQuery = Mockito.mock(TypedQuery.class);

        Mockito.when(typedQuery.getResultList()).thenReturn(mockUsers);
        Mockito.when(manager.createNamedQuery(User.GET_ALL_USERS, User.class)).thenReturn(typedQuery);


        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(user1));
        Assertions.assertTrue(result.contains(user2));
    }
}
