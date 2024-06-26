package com.ltjava.qlns.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username", length = 20, unique = true)
    @NotBlank(message = "Username là bắt buộc")
    @Size(min = 1, max = 20, message = "Username phải trong khoảng 1 và 20 kí tự")
    String username;

    @Column(name = "password", length = 250)
    @NotBlank(message = "Password là bắt buộc")
    String password;

    @Column(name = "email", length = 50, unique = true)
    @NotBlank(message = "Email là bắt buộc")
    @Size(min = 1, max = 50, message = "Email phải nhỏ hơn 50 kí tự")
    @Email
    String email;

    @Column(name = "phone", length = 10, unique = true)
    @Length(min = 10, max = 10, message = "Số điện thoại phải 10 chữ số")
    @Pattern(regexp = "^[0-9]*$", message = "Số điện thoại phải là số")
    String SDT;

    @ManyToOne
    @JoinColumn(name = "IDTrangThaiTK")
    TrangThaiAccount trangThaiAccount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "IDAccount"),inverseJoinColumns = @JoinColumn(name = "IDRole"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> userRoles = this.getRoles();
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
