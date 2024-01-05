package com.cydeo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    private LocalDateTime insertDateTime;
    @Column(nullable = false, updatable = false)
    private Long insertUserId;
    @Column(nullable = false)
    private LocalDateTime lastUpdateDateTime;
    @Column(nullable = false)
    private Long lastUpdateUserId;

    private Boolean isDeleted=false;
    @PrePersist
    //This is for spring to understand this method
    //When I do action on dB initialize this method.
    public void onPrePersist(){
        //I am initializing on it.persist=save
        //I need to tell whenever I do action in dB
        //execute this method. So I can keep track on it.
        this.insertDateTime=LocalDateTime.now();
        this.lastUpdateDateTime=LocalDateTime.now();
        //now this is hardcoded but in security portion
        // we'll make it dynamic.
        this.insertUserId=1L;
        this.lastUpdateUserId=1L;

    }
    @PreUpdate
    //This runs in the time of updating after saving data
    public void onPreUpdate(){
        this.lastUpdateDateTime=LocalDateTime.now();
        this.lastUpdateUserId=1L;
    }


}
