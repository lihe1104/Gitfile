package com.kunyong.rds.security;

import com.kunyong.rds.entity.res.Resource;
import com.kunyong.rds.entity.user.SysUser;
import com.kunyong.rds.respository.res.ResourceRepository;
import com.kunyong.rds.respository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/5/31
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    private ResourceRepository resourceRepository;



    /**
     * 这里是用来写注释的,如果我没写,那一定是因为我不想写.
     *
     * @param authentication   :用户被赋予的权限
     * @param object           :
     * @param configAttributes : 访问的资源需要的权限
     * @return : void
     * @Author: 土哥
     * @Date: 2019/5/31
     */
    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {


        Collection<? extends GrantedAuthority> userHasRoles =
                authentication.getAuthorities();

        SysUser user = (SysUser)authentication.getPrincipal();


        System.out.println("访问资源的用户为:---"+user);


        //如果访问资源不需要任何权限则直接通过
        if( configAttributes == null ) {
            return ;
        }


        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        //遍历configAttributes看用户是否有访问资源的权限

        while( ite.hasNext()){

            ConfigAttribute ca = ite.next();
            String needRole = ((SecurityConfig)ca).getAttribute();

            //ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
            for( GrantedAuthority ga: authentication.getAuthorities()){

                if(needRole.trim().equals(ga.getAuthority().trim())){

                    return;
                }

            }

        }
        /*需要在没有通过授权时抛出AccessDeniedException。*/
        throw new AccessDeniedException("");

        /*Iterator<? extends GrantedAuthority> iterator = userHasRoles.iterator();
        while (iterator.hasNext()){
            GrantedAuthority next = iterator.next();
            if ("管理员".equals(next.getAuthority())){
                return;
            }
        }

        Collection<GrantedAuthority> uriHasRoles = getGrantedAuthoritys(object);
        if (uriHasRoles == null || uriHasRoles.size() == 0) {
            return;
        }
        Optional<? extends Collection<? extends GrantedAuthority>> grantedAuthoritiesForOptional  =
                Optional.ofNullable(userHasRoles);

        try {
            grantedAuthoritiesForOptional.ifPresent(userHasRolesNotNull ->{
                userHasRolesNotNull.forEach(userHasRole -> {
                    uriHasRoles.forEach(uriHasRole -> {
                        if (userHasRole.getAuthority().equals(uriHasRole.getAuthority())) {
                            throw new AppException("break");
                        }
                    });
                });
            });
        } catch (AppException e) {
            e.printStackTrace();
            return;
        }*/
    }

    private Collection<GrantedAuthority> getGrantedAuthoritys(Object object) {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String uri = new StringBuilder(filterInvocation.getRequestUrl()).deleteCharAt(0).toString();
        if("".equals(uri)){
            return null;
        }
        List<Resource> uriHasRoles = resourceRepository.findByUrl(uri);

        if (uriHasRoles == null || uriHasRoles.size() == 0) {
            return null;
        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        uriHasRoles.forEach(item -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(item.getResName());
            grantedAuthorities.add(grantedAuthority);
        });
        return grantedAuthorities;
    }


    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
