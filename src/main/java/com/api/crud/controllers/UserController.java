package com.api.crud.controllers;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.api.crud.services.UserService;
import com.api.crud.models.UserModel;
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    // EndPoint for obtener todos los users
    @GetMapping()
    public ArrayList<UserModel> getUsers() {
        return this.userService.getUsers();
    }

    // EndPoint for guardar un user, se valida el dni
    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody UserModel user) {
        try {
            return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("DNI Existente.", HttpStatus.CONFLICT);
        }
    }

    // EndPoint for obtener un usuario por ID
    @GetMapping( path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getById(id);
    }

    // EndPoint for editar un usuario
    @PutMapping( path = "/{id}")
    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable("id") Long id) {
        return this.userService.updateById(request, id);
    }

    // EndPoint for eliminar un usuario
    @DeleteMapping( path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.userService.deleteUser(id);
        if (ok){
            return "User with id " + id + " deleted";
        }else{
            return "No pudo eliminar el usuario con id" + id;
        }
    }

    
}
