package com.example.demohe4.domain;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Entity
@Table(name="user")
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;


    public User(String name) {
        this.name = name;
    }
}
