package vn.jobhunters.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import vn.jobhunters.domain.User;
import vn.jobhunters.domain.dto.ResultPaginationDTO;
import vn.jobhunters.service.UserService;
import vn.jobhunters.service.error.IdInvalidException;

@RestController
@RequestMapping("/api/5")
public class UserController {
    private final UserService userService;

    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // get list user
    @GetMapping("/users")
    public ResponseEntity<ResultPaginationDTO> getAllUser(
            @RequestParam("current") Optional<String> currentOptional,
            @RequestParam("pageSize") Optional<String> pageSizeOptional) {
        String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
        String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";

        int current = Integer.parseInt(sCurrent);
        int pageSize = Integer.parseInt(sPageSize);

        Pageable pageable = PageRequest.of(current - 1, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchAllUserList(pageable));
    }

    // get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchUserById(id));
    }

    // create user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User postUser) {
        String hashPassword = this.passwordEncoder.encode(postUser.getPassword());
        postUser.setPassword(hashPassword);
        User ghost = this.userService.handleCreateUser(postUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(ghost);
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
