package com.ltjava.qlns.service;


import com.ltjava.qlns.model.Account;
import com.ltjava.qlns.model.Role;
import com.ltjava.qlns.repository.AccountRepository;
import com.ltjava.qlns.repository.RoleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void save(@NotNull Account account) {
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var account = accountRepository.findByUsername(username)
                .orElseThrow( ()-> new UsernameNotFoundException("Tài khoản không tồn tại") );
        return org.springframework.security.core.userdetails.User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .authorities(account.getAuthorities())
                .accountExpired(!account.isAccountNonExpired())
                .accountLocked(!account.isAccountNonLocked())
                .credentialsExpired(!account.isCredentialsNonExpired())
                .disabled(!account.isEnabled())
                .build();
    }

    // Tìm kiếm tài khoản dựa trên tên đăng nhập.
    public Optional<Account> findByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username);
    }




}
