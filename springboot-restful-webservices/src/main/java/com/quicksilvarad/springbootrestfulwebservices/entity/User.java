package com.quicksilvarad.springbootrestfulwebservices.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter //lombok annotation
@Setter //lombok annotation
@NoArgsConstructor //lombok annotation
@AllArgsConstructor
@Entity //from jakarta.persistence it specifies the class has JPA entity
@Table(name="users" ) //to configure table details also if we dont use table annotations JPA will create a default table with class name
public class User {


    @Id // used to configure primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // used for primary key generation strategy IDENTITY internally uses database provided auto increment feature to increment the primary key
    private long id;
    @Column(nullable = false) //annotation to disallow the variable to be null
    private String firstName;
    @Column(nullable = false) //annotation to disallow the variable to be null
    private String lastName;
    @Column(nullable = false, unique = true) //annotation to disallow the variable to be null and unique
    private String email;

   /* public User(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }*/
}
