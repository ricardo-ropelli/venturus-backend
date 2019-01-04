package com.venturus.business.impl;

import com.venturus.business.IUserBusiness;
import com.venturus.dto.UserDTO;
import com.venturus.entity.User;
import com.venturus.services.IUserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UserBusiness implements IUserBusiness {

    @Autowired
    private IUserService userService;

    @Override
    public UserDTO findById(final String token, final Long id) {
        User user = findUserById(token, id);
        return convertEntityToDTO(user);
    }

    @Override
    public List<UserDTO> findAllUsers(final String token, final String name, final String userName, final String offset, final String limit) {
        Integer offsetInt = Integer.parseInt(offset) - 1;
        Integer limitInt = Integer.parseInt(limit);

        Iterable<User> allUsers = userService.findAllUsers();

        List<User> usersList = StreamSupport.stream(allUsers.spliterator(), false)
                .sorted()
                .limit(Long.valueOf(limit))
                .collect(Collectors.toList());

        if (usersList.size() <= limitInt) {
            limitInt = usersList.size();
        }

        return usersList
                .subList(offsetInt, limitInt)
                .stream()
                .filter(user -> user.getToken().equals(token))
                .filter(user -> Strings.isBlank(name) ||
                        Strings.isNotBlank(name) && user.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(user -> Strings.isBlank(userName) ||
                        Strings.isNotBlank(userName) && user.getUserName().toLowerCase().contains(userName.toLowerCase()))
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long createUser(final String token, final UserDTO userDTO) {
        User user = convertDTOToEntity(token, userDTO);
        user.setToken(token);
        return userService.createUser(user).getId();
    }

    @Override
    public void updateUser(final String token, final Long id, final UserDTO userDTO) {
        User userDB = findUserById(token, id);
        User user = convertDTOToEntity(token, userDTO);
        user.setId(userDB.getId());
        userService.updateUser(user);
    }

    @Override
    public void deleteUser(final String token, final Long id) {
        this.findUserById(token, id);
        userService.deleteUser(id);
    }

    private User findUserById(final String token, final Long id) {
        Optional<User> userModel = userService.findById(id);
        wasUserFound(userModel);
        return isTokenAllowedToSeeUser(token, userModel);
    }

    private User isTokenAllowedToSeeUser(final String token, final Optional<User> userModel) {
        User user = userModel.get();
        if (!user.getToken().equals(token)) {
            throw new RuntimeException("Forbidden - HttpStatus 403");
        }
        return user;
    }

    private void wasUserFound(final Optional<User> userModel) {
        if (!userModel.isPresent()) {
            throw new RuntimeException("Not found - HttpStatus 404");
        }
    }

    private UserDTO convertEntityToDTO(final User user) {
        return new UserDTO(user.getId(),
                user.getUserName(),
                user.getName(),
                user.getEmail(),
                user.getCity(),
                user.getRideGroup(),
                user.getPosts(),
                user.getAlbums(),
                user.getPhotos());
    }

    private User convertDTOToEntity(final String token, final UserDTO userDTO) {
        return new User(null,
                userDTO.getUserName(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getCity(),
                userDTO.getRideGroup(),
                userDTO.getPosts(),
                userDTO.getAlbums(),
                userDTO.getPhotos(),
                token);
    }
}
