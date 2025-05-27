package com.devsuperior.webservicesjpa.services;

import com.devsuperior.webservicesjpa.entities.User;
import com.devsuperior.webservicesjpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
}
