package com.kunyong.rds.security;

import com.kunyong.rds.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 认证管理
 *
 * @author 贺
 * @create 2019/5/31
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MyUserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String passwd = (String) authentication.getCredentials();
        UserDetails user = userDetailsService.loadUserByUsername(username);
        /*passwdEncoder().matches(passwd,user.getPassword())*/
        try {
            if (MD5Util.verify(passwd,user.getPassword())){
                Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
                return  new UsernamePasswordAuthenticationToken(user,passwd,authorities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new BadCredentialsException("Wrong password");
    }

    private PasswordEncoder passwdEncoder() {
        return  new BCryptPasswordEncoder();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
