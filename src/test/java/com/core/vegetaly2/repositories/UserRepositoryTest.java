package com.core.vegetaly2.repositories;

import com.core.vegetaly2.models.User;
import com.core.vegetaly2.utils.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .username("test")
                .password("12345678")
                .role(Role.USER)
                .email("fake@fakemail.com")
                .build();
        testEntityManager.persist(user);
    }

    @Test
    public void findByUsernameFound() {
        Optional<User> userData = userRepository.findByUsername("test");
        User user = userData.get();
        assertEquals("12345678", user.getPassword());
        assertEquals("test", user.getUsername());
        assertEquals(Role.USER, user.getRole());
        assertEquals("fake@fakemail.com", user.getEmail());
    }

    @Test
    public void findByUsernameNotFound() {
        Optional<User> userData = userRepository.findByUsername("test2");
        assertTrue(userData.isEmpty());
    }

}