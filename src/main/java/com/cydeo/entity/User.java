package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private boolean enabled;
    private String phone;

    //Many users can be assigned to one role
    //If it is Many to one it is enough for us
    // where to create foreign key
    //Now owner is the user table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    @Enumerated(EnumType.STRING)
    private Gender gender;

}
