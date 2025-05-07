package com.shop.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "email",
                columnNames = "email"
        )
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    @Column
    private String firstName;
    @Getter
    @Setter
    @Column(name = "fullname")
    private String fullName;

    @Email
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
