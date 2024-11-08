package com.jhome.user.domain;

import com.jhome.user.dto.UserRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity(name = "JHOME_USER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String role;
    private String type;

    private String name;
    private String email;
    private String phone;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void edit(UserRequest request) {
        this.username = Optional.ofNullable(request.getUsername()).orElse(this.username);
        this.password = Optional.ofNullable(request.getPassword()).orElse(this.password);
        this.role = Optional.ofNullable(request.getRole()).orElse(this.role);
        this.type = Optional.ofNullable(request.getType()).orElse(this.type);
        this.name = Optional.ofNullable(request.getName()).orElse(this.name);
        this.email = Optional.ofNullable(request.getEmail()).orElse(this.email);
        this.phone = Optional.ofNullable(request.getPhone()).orElse(this.phone);
    }

}
