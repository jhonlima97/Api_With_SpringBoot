package com.api.crud.repositories;
import com.api.crud.models.UserModel;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {

    public abstract ArrayList<UserModel> findByPrioridad(Integer prioridad);

    
}
