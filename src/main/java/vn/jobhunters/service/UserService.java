package vn.jobhunters.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import vn.jobhunters.domain.User;
import vn.jobhunters.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // create user
    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    // get all user
    public List<User> fetchAllUserList() {
        return this.userRepository.findAll();
    }

    // get user by id
    public User fetchUserById(long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    // update user
    public User handleUpdateUserById(User reqUser) {
        User currentUser = this.fetchUserById(reqUser.getId());
        if (currentUser != null) {
            currentUser.setName(reqUser.getName());
            currentUser.setAddress(reqUser.getAddress());
            currentUser.setAge(reqUser.getAge());

            // update
            this.userRepository.save(currentUser);
        }
        return null;
    }

    // delete user by id
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }
}
