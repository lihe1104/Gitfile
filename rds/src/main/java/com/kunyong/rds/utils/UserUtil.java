package com.kunyong.rds.utils;

import com.kunyong.rds.entity.user.SysUser;
import com.kunyong.rds.respository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/2
 */
public class UserUtil {

    @Autowired
    private static UserRepository userRepository;

    public static User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return (User) authentication.getPrincipal();
            }
        }

        return null;
    }

    public static SysUser getSysUser(){
        User user = getLoginUser();
        SysUser sysUser = userRepository.findByTel(user.getUsername());
        return sysUser;
    }
}
