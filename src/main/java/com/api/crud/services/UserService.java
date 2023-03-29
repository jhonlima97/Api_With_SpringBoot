package com.api.crud.services;
import com.api.crud.models.UserModel;
import com.api.crud.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<UserModel> obtenerUsuarios(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }
     
    public UserModel guardarUsuario(UserModel usuario){
        return userRepository.save(usuario);
    }

    public Optional<UserModel> obtenerPorId(Long id){
        return userRepository.findById(id);
    }

    public ArrayList<UserModel>  obtenerPorPrioridad(Integer prioridad) {
        return userRepository.findByPrioridad(prioridad);
    }

    public boolean eliminarUsuario(Long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

}
