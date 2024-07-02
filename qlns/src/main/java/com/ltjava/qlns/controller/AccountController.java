package com.ltjava.qlns.controller;


import com.ltjava.qlns.model.Account;
import com.ltjava.qlns.model.Role;
import com.ltjava.qlns.model.TrangThaiAccount;
import com.ltjava.qlns.service.AccountService;
import com.ltjava.qlns.service.RoleService;
import com.ltjava.qlns.service.TrangThaiAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TrangThaiAccountService trangThaiAccountService;

    @GetMapping("/edit")
    public String editAccount(@AuthenticationPrincipal UserDetails userDetails, Model model){

        Account account = accountService.findByUsername(userDetails.getUsername()).orElseThrow();

        model.addAttribute("account",account);
        return "/account/update-account";

    }

    @PostMapping("/update/{id}")
    public String updateAccount(@PathVariable("id") Long id, @Valid Account account, BindingResult result) {
        if (result.hasErrors()) {
            account.setId(id);
            return "/account/update-account";
        }
        accountService.updateAccount(account);

        return "/home";
    }


    @GetMapping("/phanquyen")
    public String listAccount(Model model){
        List<Account> accounts = accountService.getAllAccounts();
        List<Role> allRoles = roleService.getAllRoles();
        model.addAttribute("accounts", accounts);
        model.addAttribute("allRoles", allRoles);
        return "/account/account-list";

    }

    @PostMapping("/editRoles/{id}")
    public String editRoles(@PathVariable("id") Long accountId, @RequestParam("roles") List<Long> roleIds) {
        Account account = accountService.findById(accountId);
        Set<Role> roles = roleIds.stream()
                .map(roleService::findById)
                .collect(Collectors.toSet());
        account.setRoles(roles);
        accountService.save(account);
        return "redirect:/account/phanquyen"; // Redirect to account list page after saving
    }


}
