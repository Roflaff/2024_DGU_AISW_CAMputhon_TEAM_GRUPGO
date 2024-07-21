package rofla.back.back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "grupgo")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "username", nullable = false, length = 10)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "phone_num", length = 10)
    private String phoneNum;

    @Column(name = "major", nullable = false, length = 30)
    private String major;

    @Column(name = "role", nullable = false, length = 2)
    private String role;

}