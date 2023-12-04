package com.makesoftware.siga.security;

import com.makesoftware.siga.repository.UserRepository;
import com.makesoftware.siga.util.UserUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthenticationFilter(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if (token != null) {
            String login = tokenService.validateToken(token);

            UserDetails userDetails = UserUtils.findUserByLogin(login, userRepository);

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String requestAuthHeader = request.getHeader("Authorization");
        if (requestAuthHeader == null || requestAuthHeader.isBlank() || !requestAuthHeader.startsWith("Bearer ")) {
            return null;
        }

        return requestAuthHeader.replace("Bearer ", "");
    }
}
