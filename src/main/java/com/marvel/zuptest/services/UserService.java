package com.marvel.zuptest.services;

import com.marvel.zuptest.exceptions.EntityNotUniqueException;
import com.marvel.zuptest.models.Comics;
import com.marvel.zuptest.repositories.UserRepository;
import com.marvel.zuptest.exceptions.EntityNotFoundException;
import com.marvel.zuptest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        isValid(user);
        user.setId(null);

        return userRepository.save(user);
    }

    public User findUserById(Long id){
        Optional<User> obj = userRepository.findById(id);

        if(obj.isEmpty()){
            throw new EntityNotFoundException("User ID: "+ id + " not found");
        }
        List<Comics> comics = obj.get().getComics();
        for (Comics comic : comics ){
            boolean discountDay = isDiscountDay(comic);
            comic.setDiscount(discountDay);
            if(discountDay){
                BigDecimal discountValue = comic.getPrice().multiply(new BigDecimal(0.1));
                comic.setPrice(comic.getPrice().subtract(discountValue).setScale(2, RoundingMode.HALF_EVEN));
            }
        }
        obj.get().setComics(comics);
        return obj.get();
    }

    public Boolean isDiscountDay(Comics comic){
        DayOfWeek now = LocalDate.now().getDayOfWeek();
        if(now.equals(comic.getIsbnDay())){
            return true;
        }
        return false;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void updateUser(User user){
        User newObj = findUserById(user.getId());
        updateData(newObj, user);
        userRepository.save(newObj);
    }

    public void addComicsToUser(User user){
        User newObj = findUserById(user.getId());
        addComics(newObj, user);
        userRepository.save(newObj);
    }

    public void deleteUser(Long id){
        findUserById(id);
        userRepository.deleteById(id);
    }

    public void isValid(User user){
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

        if(user.getName() == null || user.getEmail() == null ||
                user.getCpf() == null || user.getBirthdate() == null){
            throw new NullPointerException("All fields are required");
        }

        if(user.getName().isBlank() || user.getEmail().isBlank() ||
                user.getCpf().isBlank() || user.getBirthdate().toString().isBlank()){
            throw new IllegalArgumentException("All fields are required");
        }
    }

    private void updateData(User newObj, User obj){
        newObj.setName(obj.getName());
        newObj.setBirthdate(obj.getBirthdate());
    }

    private void addComics(User newObj, User obj){
        List<Comics> comics = obj.getComics();
        for(Comics userComics : newObj.getComics()){
            comics.add(userComics);
        }
        newObj.setComics(comics);
    }
}
