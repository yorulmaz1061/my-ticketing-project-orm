package com.cydeo.repository;

import com.cydeo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    //Build all queries that will bring data from db.
    //put ready queries with the help of JPA interface. There are 20 methods here
    // put derive query or @Query(JPQL-Native) if needed

}
