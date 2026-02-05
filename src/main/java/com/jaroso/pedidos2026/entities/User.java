package com.jaroso.pedidos2026.entities;


import com.jaroso.pedidos2026.security.UserAuthority;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(unique = true , nullable = false)
    private String userName;

    private String password;

    private String email;

  //Aqui pondremos la relacion de user y pedido


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<UserAuthority> authorities = new ArrayList<>();

    public User(Long id, String username, String password, String email, List<UserAuthority> authorities) {
        this.id = id;
        this.userName = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.toString()))
                .toList();
    }
}
