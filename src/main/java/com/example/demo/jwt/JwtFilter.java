package com.example.demo.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.security.CustomUserDetailService;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtill jwtUtill;
	
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	  @Override
	    protected boolean shouldNotFilter(HttpServletRequest request) {
	        String path = request.getServletPath();
	        return path.equals("/signin")
	            || path.equals("/login")
	            || path.equals("/verify-email")
	            || path.equals("/home");
	    }

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	HttpServletResponse response,
	FilterChain filterChain)
	throws ServletException, IOException {

//	String authHeader = request.getHeader("Authorization");
//
//	if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//	filterChain.doFilter(request, response);
//	return;
		
		 if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	            response.setStatus(HttpServletResponse.SC_OK);
	            return;
	        }
		 
		String token = getJwtFromCookies(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;

}
	
//	String token = authHeader.substring(7);

        try {
            String userEmail = jwtUtill.ExstractUserEmail(token);

            if (userEmail != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails =
                        customUserDetailService.loadUserByUsername(userEmail);

                if (jwtUtill.ValidateToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromCookies(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if ("jwt".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}