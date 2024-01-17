package com.cydeo.entity;

import com.cydeo.enums.Gender;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
//@SQLRestriction("is_deleted=false")
@Where(clause = "is_deleted=false")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;

    //userName should be unique
    @Column(unique = true,nullable = false)
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
