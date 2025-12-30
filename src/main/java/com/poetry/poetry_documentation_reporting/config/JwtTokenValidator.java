package com.poetry.poetry_documentation_reporting.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenValidator extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public JwtTokenValidator(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(JwtConstant.JWT_HEADER);

        if (header != null && header.startsWith(JwtConstant.TOKEN_PREFIX)) {
            String token = header.substring(JwtConstant.TOKEN_PREFIX.length());

            try {
                if (jwtProvider.validateToken(token)) {
                    String email = jwtProvider.getEmailFromToken(token);
                    String roles = jwtProvider.getRolesFromToken(token);

                    Authentication auth = new UsernamePasswordAuthenticationToken(
                            email, null, AuthorityUtils.commaSeparatedStringToAuthorityList(roles)
                    );

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (ExpiredJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\": \"Token expired\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}