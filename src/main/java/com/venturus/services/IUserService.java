package com.venturus.services;

import com.venturus.entity.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> findById(Long id);

    Iterable<User> findAllUsers();

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);
}
