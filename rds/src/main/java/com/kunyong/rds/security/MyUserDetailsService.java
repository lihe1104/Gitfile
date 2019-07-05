package com.kunyong.rds.security;

import com.kunyong.rds.entity.res.Resource;
import com.kunyong.rds.entity.role.ResRoleLink;
import com.kunyong.rds.entity.role.UserRoleLink;
import com.kunyong.rds.entity.user.SysUser;
import com.kunyong.rds.respository.role.ResRoleLinkRepository;
import com.kunyong.rds.respository.user.UserRepository;
import com.kunyong.rds.respository.res.ResourceRepository;
import com.kunyong.rds.respository.user.UserRoleLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/5/31
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleLinkRepository userRoleLinkRepository;

    @Autowired
    private ResourceRepository resourceRespository;

    @Autowired
    private ResRoleLinkRepository resRoleLinkRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser u = userRepository.findByTel(username);
        if (u == null ) {
            throw new UsernameNotFoundException("username not found");
        }
       /* UserRoleLink userRoleLink = new UserRoleLink();
        userRoleLink.setUserId(u.getId());*/
        List<UserRoleLink> userRoleLinks = userRoleLinkRepository.findByUserId(u.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Integer> roleIds = new ArrayList<>();
        Optional<List<UserRoleLink>> userRoleLinksOptional = Optional.ofNullable(userRoleLinks);
        userRoleLinksOptional.ifPresent(userRoleLinks1 -> {
            userRoleLinks1.forEach(userRoleLink1 -> {
                roleIds.add(userRoleLink1.getRoleId());
                if (userRoleLink1.getRoleId() == 1L) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_root");//root角色特权
                    grantedAuthorities.add(grantedAuthority);
                }
            });
        });


        List<ResRoleLink> resRoleLinks = resRoleLinkRepository.findByRoleIdIn(roleIds);
        List<Integer> resIds = new ArrayList<>();
        Optional<List<ResRoleLink>> links = Optional.ofNullable(resRoleLinks);
        links.ifPresent(linksNotNull -> {
            linksNotNull.forEach(l -> {
                resIds.add(l.getId());
            });
        });
        List<Resource> resources = resourceRespository.findByIdIn(resIds);
        if (resources != null && resources.size() > 0) {
            resources.forEach(resource -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(resource.getResName());//必须ROLE_为前缀
                grantedAuthorities.add(grantedAuthority);
            });
        }
        return new User(username, u.getPasswd(), true, true, true, true, grantedAuthorities);
    }
}
