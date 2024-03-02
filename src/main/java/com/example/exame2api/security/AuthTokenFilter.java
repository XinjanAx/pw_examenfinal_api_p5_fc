package com.example.pw_api_u3_p5_fc.security;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    private static final Logger LOG=LoggerFactory.getLogger(JwtUtils.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        try{
            String jwt = this.parseJwt(request);
            if(jwt!=null && this.jwtUtils.validateJwtToken(jwt)){
                //generamos una autenticacion
                //necesitamos el nombre
                String username=this.jwtUtils.getUserNameFromJetToken(jwt);
                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                        null,
                        new ArrayList<>());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch(Exception e){
            LOG.error("ERRoRRRRRRRR", e);
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        String hederAtuh = request.getHeader("Authorisation");
        if(StringUtils.hasText(hederAtuh)&&hederAtuh.startsWith("Bearer ")){
            return hederAtuh.substring(7,hederAtuh.length());
        }
        return null;

    }
    
}
