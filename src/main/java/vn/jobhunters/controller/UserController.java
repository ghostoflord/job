package vn.jobhunters.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import vn.jobhunters.domain.User;
import vn.jobhunters.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get list user
    @GetMapping("/users")
    public List<User> getAllUser() {
        return this.userService.fetchAllUserList();
    }

    // get user by id
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return this.userService.fetchUserById(id);
    }

    // create user
    @PostMapping("/users")
    public User updateUser(@RequestBody User postUser) {
        return this.userService.handleCreateUser(postUser);
    }

    // delete user
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
    }

    // update user
    @PutMapping("/users")
    public User updateUserById(@RequestBody User putUser) {
        User ghostUser = this.userService.handleUpdateUserById(putUser);
        return ghostUser;
    }

}
