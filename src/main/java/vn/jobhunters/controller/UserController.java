package vn.jobhunters.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import vn.jobhunters.domain.User;
import vn.jobhunters.service.UserService;
import vn.jobhunters.service.error.IdInvalidException;

@RestController
@RequestMapping("/api/5")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get list user
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchAllUserList());
    }

    // get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchUserById(id));
    }

    // create user
    @PostMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User postUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.handleCreateUser(postUser));
    }

    // delete user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") long id) throws IdInvalidException {
        if (id >= 55) {
            throw new IdInvalidException("id qua lon khong xu ly duoc");
        }
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete success");
    }

    // update user
    @PutMapping("/users")
    public ResponseEntity<User> updateUserById(@RequestBody User putUser) {
        User ghostUser = this.userService.handleUpdateUserById(putUser);
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.handleCreateUser(ghostUser));
    }
}
