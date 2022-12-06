package com.abistudio.testspringboot.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.websocket.ClientEndpoint;
import java.util.Collection;

@Entity
@Table(name = "user")
public class UserInfo  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    @NotNull
    @Length(min = 5, max = 50)
    private String username;

    @Column(nullable = false, length = 64)
    @NotNull
    @Length(min = 8, max = 64)
    private String password;

    @Nullable
    @Length(max = 50)
    private String name;

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    @Nullable
    @Length(max = 50)
    private String address;

    public UserInfo() {
    }

    public UserInfo(String username, String password, @Nullable String name, @Nullable String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
