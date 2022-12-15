package com.abistudio.testspringboot;

import com.abistudio.testspringboot.jwt.JwtTokenFilter;
import com.abistudio.testspringboot.user.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class ApplicationSecurityConfiguration {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public UserDetailsService userDetailsService (){
        return new CustomUserDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
        throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests()
                //TODO 7. Lindungi microservice yang dibuat pada nomor 2 dengan authentikasi berbasis JWT, dan biarkan microservices yang dibuat pada nomor 6 tetap terbuka (dapat diakses siapapun).
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().permitAll();

        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(
                        ((request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        })
                );
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
