package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User findByEmailAndPassword(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user; // Return user if credentials match
        }
        return null; // Return null if user not found or passwords don't match
    }




    // Add other user-related methods (like update, delete) as needed
}
