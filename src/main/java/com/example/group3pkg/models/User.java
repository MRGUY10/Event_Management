package com.example.group3pkg.models;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column (unique = true)
    private String password;
    @Column (unique = true)
    private String role;
    @Column (unique = true)
    private String fullname;

    public User() {
        super();
    }

    public User(String email, String password, String role, String fullname) {

        this.email = email;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
    }
}







