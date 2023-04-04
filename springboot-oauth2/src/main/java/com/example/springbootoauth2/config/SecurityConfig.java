package com.example.springbootoauth2.config;

import com.example.springbootoauth2.exception.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: Fangjx
 * @Date: 15:39  2023/4/4
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Bean
    public PasswordEncoder getPwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/error.html").permitAll()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/main.html").hasRole("abc")
                .antMatchers("/main.html").hasAuthority("admin")
                .antMatchers("/main.html").hasAnyAuthority("admin,norma")
                .antMatchers("/main.html").hasAnyRole("ABC,abc")
                //通过指定ip地址进行访问,注意这里的ip与localhost转换的ip是不一样的，线上一般为服务器ip
                .antMatchers("/main.html").hasIpAddress("127.0.0.1")
                //所有请求都必须被认证，必须登录之后被访问
                .anyRequest().authenticated();

                //关闭csrf防护
                http.csrf().disable();

                http.exceptionHandling()
                        .accessDeniedHandler(myAccessDeniedHandler);

    }

    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((authz) -> authz
//                .anyRequest()
//                .authenticated()
//        )
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }


}
