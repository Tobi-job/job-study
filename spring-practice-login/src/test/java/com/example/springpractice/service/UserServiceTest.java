package com.example.springpractice.service;

import com.example.springpractice.entity.User;
import com.example.springpractice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(UserService.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void updateUser_shouldChangeUsernameRoleAndPassword() {
        userService.registerUser("alice", "pass123");

        User user = userRepository.findByUsername("alice").orElseThrow();
        userService.updateUser(user.getId(), "alice-updated", "newpass456", "ADMIN");

        User updated = userRepository.findByUsername("alice-updated").orElseThrow();
        assertThat(updated.getUsername()).isEqualTo("alice-updated");
        assertThat(updated.getRole()).isEqualTo("ADMIN");
        assertThat(userService.authenticate("alice-updated", "newpass456")).isTrue();
    }
}
