package com.projectname.ProjectName.repository;

import com.projectname.ProjectName.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        // Create and save a user
        User user = new User();
        user.setEmail("user@test.com");
        user.setUsername("user123");
        user.setPassword("securePassword");

        User savedUser = userRepository.save(user);

        // Assert that the user was saved correctly
        assertNotNull(savedUser.getEmail(), "Email should not be null");
        assertEquals("user123", savedUser.getUsername(), "UserName should match");
        assertEquals("securePassword", savedUser.getPassword(), "Password should match");
    }

    @Test
    public void testFindUserByUsername() {
        // Create and save a user
        User user = new User();
        user.setEmail("finduser@test.com");
        user.setUsername("findme");
        user.setPassword("findmePassword");

        userRepository.save(user);

        // Find the user by username
        User foundUser = userRepository.findUserByUsername("findme");

        // Assert that the user was found by username
        assertNotNull(foundUser, "User should not be null");
        assertEquals("findme", foundUser.getUsername(), "UserName should match");
        assertEquals("finduser@test.com", foundUser.getEmail(), "Email should match");
    }

    @Test
    public void testFindUserByEmail() {
        // Create and save a user
        User user = new User();
        user.setEmail("emailuser@test.com");
        user.setUsername("emailuser123");
        user.setPassword("emailPassword");

        userRepository.save(user);

        // Find the user by email
        User foundUser = userRepository.findUserByEmail("emailuser@test.com");

        // Assert that the user was found by email
        assertNotNull(foundUser, "User should not be null");
        assertEquals("emailuser@test.com", foundUser.getEmail(), "Email should match");
        assertEquals("emailuser123", foundUser.getUsername(), "UserName should match");
    }
}
