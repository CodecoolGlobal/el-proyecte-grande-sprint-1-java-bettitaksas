package com.fridgemaster.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fridge_id")
    private Fridge fridge;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
