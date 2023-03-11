package com.grooming.groomingreservationsystem.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        try {
            return userRepository.findById(username).get();
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }
    }

    public boolean deleteUser(String username, String password) {
        try {
            User deleteThisUser = userRepository.findById(username).get();
            if (userRepository.existsById(username)) {
                if (deleteThisUser.getPassword() == password) {
                    userRepository.deleteById(username);
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
        }
}
