package com.example.usermanagement.service;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"users"})
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Cacheable
    public User getUser(Long id){
        return repo.findById(id).orElseThrow();
    }

    public List<User> getAll(){
        return repo.findAll();
    }

    public User createUser(User user){
        return repo.save(user);
    }

    @CacheEvict
    public void deleteUser(Long id){
        repo.deleteById(id);
    }
}
