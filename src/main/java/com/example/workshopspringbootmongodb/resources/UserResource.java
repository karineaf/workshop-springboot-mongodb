package com.example.workshopspringbootmongodb.resources;

import com.example.workshopspringbootmongodb.domain.User;
import com.example.workshopspringbootmongodb.dto.UserDTO;
import com.example.workshopspringbootmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = service.findAll();
        List<UserDTO> listDTO = users.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
