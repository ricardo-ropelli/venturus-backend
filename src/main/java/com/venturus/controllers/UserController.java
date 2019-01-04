package com.venturus.controllers;

import com.venturus.business.IUserBusiness;
import com.venturus.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private IUserBusiness userBusiness;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestHeader String token,
                                                     @RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String userName,
                                                     @RequestParam(required = false, defaultValue = "1") String _offset,
                                                     @RequestParam(required = false, defaultValue = "5") String _limit) {

        return ResponseEntity.ok(userBusiness.findAllUsers(token, name, userName, _offset, _limit));
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDTO> getUserById(@RequestHeader(value = "token") String token,
                                               @PathVariable Long userId) {

        return ResponseEntity.ok(userBusiness.findById(token, userId));
    }

    @PostMapping
    public ResponseEntity<Long> createUser(UriComponentsBuilder uriComponentsBuilder,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody UserDTO userDTO) {

        return ResponseEntity.created(
                uriComponentsBuilder.path("/users/{userId}")
                        .buildAndExpand(userBusiness.createUser(token, userDTO))
                        .toUri())
                .build();
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<Void> updateUser(@RequestHeader(value = "token") String token,
                                           @PathVariable Long userId,
                                           @RequestBody UserDTO userDTO) {

        userBusiness.updateUser(token, userId, userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@RequestHeader(value = "token") String token,
                                           @PathVariable Long userId) {

        userBusiness.deleteUser(token, userId);
        return ResponseEntity.noContent().build();
    }
}
