package com.devsuperior.webservicesjpa.services;

import com.devsuperior.webservicesjpa.entities.User;
import com.devsuperior.webservicesjpa.repositories.UserRepository;
import com.devsuperior.webservicesjpa.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User update(Long id, User user) {
        User entity = userRepository.getReferenceById(id);

        entity.setName(user.getName() != null ? user.getName() : entity.getName());
        entity.setEmail(user.getEmail() != null ? user.getEmail() : entity.getEmail());
        entity.setPassword(user.getPassword() != null ? user.getPassword() : entity.getPassword());
        entity.setPhone(user.getPhone() != null ? user.getPhone() : entity.getPhone());

        return userRepository.save(entity);
    }
}
