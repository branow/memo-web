package com.branow.memoweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    private Integer userId;

    @NotEmpty
    @NotBlank
    private String username;

    @Email
    private String email;

    private String password;

    private String description;

    private boolean enable;

    @OneToMany
    @JoinColumn(name = "user")
    private List<Module> modules;

}
