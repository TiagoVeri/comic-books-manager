package com.marvel.zuptest.services;

import com.marvel.zuptest.UserRepository;
import com.marvel.zuptest.exceptions.EntityNotFoundException;
import com.marvel.zuptest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        user.setId(null);
        return userRepository.save(user);
    }

    public User findUserById(Long id){
        Optional<User> obj = userRepository.findById(id);

        return obj.orElseThrow(() -> {
            throw new EntityNotFoundException("User ID: "+ id + " not found");
        });
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void updateUser(User user){
         userRepository.save(user);
    }

    public void deleteUser(Long id){
        findUserById(id);
        userRepository.deleteById(id);
    }
}
