package com.api.crud.services;
import com.api.crud.models.UserModel;
import com.api.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    // Service for obtener todos los usuarios
    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }
    
    // Service for guardar un usuario
    public UserModel saveUser(UserModel user){
        if (user == null) {
            return null;
        }
        return userRepository.save(user);
    }

    // Service for obtener un usuario por ID
    public Optional<UserModel> getById(Long id){
        if (id == null) {
            return null;
        }
        return userRepository.findById(id);
    }

    // Service for editar un usuario
    public UserModel updateById(UserModel request, Long id) {
        if (request == null || id == null) {
            return null;
        }
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setDni(request.getDni());
            user.setPhone(request.getPhone());
            user.setStatus(request.getStatus());

            userRepository.save(user);
            return user;
        } else {
            return null;
        }
    }

    // Service for eliminar un usuario
    public boolean deleteUser(Long id) {
        if (id == null) {
            return false;
        }
        try {
            userRepository.deleteById(id);
            return true;
        } catch(Exception err) {
            return false;
        }
    }

}
