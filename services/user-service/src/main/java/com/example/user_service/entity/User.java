package com.example.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //maps to a database table
@Table(name = "users") //it's ok even if you dont write the table name JPA will use the entity class name as the table name ie user
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   //@coloumn Maps field to a DB column, unique means there cannot exist two same email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    //if you dont use any annotations like @Column then also default mapping will be to db coloumn only
    private String name;
    private String phone;

    // @enumerated Tells JPA this is an enum.
    @Enumerated(EnumType.STRING) //stores value as text in db
    private Role role = Role.USER;
    //uses my Role enum and keeps default as user unless changed
}