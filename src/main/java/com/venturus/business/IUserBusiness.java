package com.venturus.business;

import com.venturus.dto.UserDTO;

import java.util.List;

public interface IUserBusiness {

    UserDTO findById(String token, Long id);

    List<UserDTO> findAllUsers(String token, String name, String userName, String offset, String limit);

    Long createUser(String token, UserDTO userDTO);

    void updateUser(String token, Long id, UserDTO userDTO);

    void deleteUser(String token, Long id);
}
