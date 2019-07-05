package com.kunyong.rds.config;

import com.kunyong.rds.security.MyAccessDecisionManager;
import com.kunyong.rds.security.MyAuthenticationProvider;
import com.kunyong.rds.security.MyPersistentTokenRepository;
import com.kunyong.rds.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/5/31
 */
@Configuration
@ComponentScan("com.kunyong")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
@PropertySource("classpath:application.yml")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private MyAuthenticationProvider authenticationProvider;

    private MyUserDetailsService userDetailsService;

    private MyPersistentTokenRepository persistentTokenRepository;

    private MyAccessDecisionManager accessDecisionManager;

    @Autowired
    public WebSecurityConfig(MyAuthenticationProvider authenticationProvider,
                             MyUserDetailsService userDetailsService,
                             MyPersistentTokenRepository persistentTokenRepository,
                             MyAccessDecisionManager accessDecisionManager) {
        this.authenticationProvider = authenticationProvider;
        this.userDetailsService = userDetailsService;
        this.persistentTokenRepository = persistentTokenRepository;
        this.accessDecisionManager = accessDecisionManager;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();

        http.formLogin().loginPage("/login").loginProcessingUrl("/login/form")
                .successForwardUrl("/index")
                .failureHandler(new SimpleUrlAuthenticationFailureHandler(){
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json;charset=utf-8");
                        String s = "";
                        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                            s="用户名或密码输入错误，登录失败!";

                        } else if (e instanceof DisabledException) {
                           s="账户被禁用，登录失败，请联系管理员!";
                        } else {
                            s="登录失败!";
                        }
                        String redirectUrl = "/login-error?message=" + URLEncoder.encode(s,"UTF-8");
                        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
                    }
                })
                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .authorizeRequests()
                .antMatchers("/session/invalid","/css/**", "/js/**","/images/**","/common/**","/webfonts/**","/admin-lte/**","/role/**","/login-error").permitAll()
                .anyRequest()
                .authenticated()
                //定义那些URL需要被保护，哪些不需要被保护
                .and()
                .sessionManagement().invalidSessionUrl("/session/invalid")
                .and()
                .csrf().disable();
    }
/*
    @Bean
    public RememberMeServices rememberService() {
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices("key", userDetailsService, persistentTokenRepository);
        rememberMeServices.setCookieName("remember-me");
        rememberMeServices.setParameter("remember-me");
        rememberMeServices.setTokenValiditySeconds(864000);
        return rememberMeServices;
    }*/
}
