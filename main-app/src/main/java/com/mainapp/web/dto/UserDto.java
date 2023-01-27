package com.mainapp.web.dto;

import com.mainapp.security.AuthorityEntity;
import com.mainapp.service.data.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OrderBy;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String realName;
    private String password;
    private String plainPassword;
    private String confirmPassword;
    private Set<AuthorityEntity> authorities = new HashSet<>();
    @OrderBy(clause = "id")
    private Set<UserAccount> accounts = new TreeSet<>();
}
