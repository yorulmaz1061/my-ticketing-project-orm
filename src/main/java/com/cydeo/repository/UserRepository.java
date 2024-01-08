package com.cydeo.repository;

import com.cydeo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);

    @Transactional
    //@Modifying  if you use jpql or native query with ddl operation.
    //for persisting or deleting we need transactional
    void  deleteByUserName(String username);

    List<User> findAllByRoleDescriptionIgnoreCase (String description);
    //derived query


}
