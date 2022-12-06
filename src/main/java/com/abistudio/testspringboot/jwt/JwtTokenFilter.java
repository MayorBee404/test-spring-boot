package com.abistudio.testspringboot.jwt;

import com.abistudio.testspringboot.myuser.MyUser;
import com.abistudio.testspringboot.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtility jwtTokenUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        if (hasAuthorizationBearer(request)){
            String accessToken = getAccessToken(request);
            if (jwtTokenUtility.validateAccessToken(accessToken)){
                setAuthenticationContext(accessToken,request);
            }
        }
        filterChain.doFilter(request,response);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
        return true;
    }

    private String getAccessToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }

    private void setAuthenticationContext(String accessToken,HttpServletRequest request){

        UserDetails userDetails = getUserDetails(accessToken);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,null);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }

    private UserInfo getUserDetails(String token){
        UserInfo userInfo = new UserInfo();
        String username =  jwtTokenUtility.getUsername(token);
        userInfo.setUsername(username);
        return userInfo;
    }
}
