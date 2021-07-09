package com.marvel.zuptest.services;

import com.marvel.zuptest.exceptions.EntityNotUniqueException;
import com.marvel.zuptest.repositories.UserRepository;
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
        verifyNotUniqueUser(user);
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

    public void verifyNotUniqueUser(User user){
        User objCpf = userRepository.findByCpf(user.getCpf());
        User objEmail = userRepository.findByEmail(user.getEmail());

        if(objCpf != null && objEmail != null){
            throw new EntityNotUniqueException("The field CPF and EMAIL must be unique");
        }
        if(objCpf != null){
            throw new EntityNotUniqueException("The field CPF must be unique");
        }
        if(objEmail != null){
            throw new EntityNotUniqueException("The field EMAIL must be unique");
        }
    }
}
