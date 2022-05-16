package com.example.demo.configurations.security;

import com.example.demo.exceptions.RestAccessDeniedHandler;
import com.example.demo.exceptions.RestAuthenticationEntryPoint;
import com.example.demo.security.filters.JWTAuthFilter;
import com.example.demo.services.implementations.UserDetail.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Autowired
    private RestAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/api/auth/login_admin").permitAll()
                .antMatchers("/api/auth/register").permitAll()
                .antMatchers("/api/auth/refresh_tokens").permitAll()
                .antMatchers("/api/product/search").permitAll()
                .antMatchers("/api/sport").permitAll()
                .antMatchers("/api/technology").permitAll()
                .antMatchers("/api/gender").permitAll()
                .antMatchers("/api/product/{\\d+}").permitAll()
                .antMatchers("/api/product/ratings/*").permitAll()
                .antMatchers("/api/product/search").permitAll()
//                .antMatchers("/api/admin/products/delete").permitAll()
//                .antMatchers("/api/admin/products/create").permitAll()
//                .antMatchers("/api/admin/products/update").permitAll()
//                .antMatchers("/api/admin/users/*").permitAll()
//                .antMatchers("/api/admin/users/assign_role").permitAll()
//                .antMatchers("/api/admin/role").permitAll()
//                .antMatchers("/api/admin/category").permitAll()
//                .antMatchers("/api/admin/size").permitAll()
//                .antMatchers("/api/admin/technology").permitAll()
//                .antMatchers("/api/admin/sport").permitAll()
                .antMatchers("/api/category").permitAll()
                .antMatchers("/api/size").permitAll()
                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/webjars/**", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint);

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }
}
