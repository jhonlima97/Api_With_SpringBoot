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

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }
     
    public UserModel saveUser(UserModel user){
        if (user == null) {
            return null;
        }
        return userRepository.save(user);
    }

    public Optional<UserModel> getById(Long id){
        if (id == null) {
            return null;
        }
        return userRepository.findById(id);
    }

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
            userRepository.save(user);
            return user;
        } else {
            return null;
        }
    }    

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
