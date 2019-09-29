package com.meetu.gateway.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Role;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Authenticate requests with header 'Authorization: Bearer jwt-token'.
 *
 * @author raj
 */
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationConfig config;

    public JwtTokenAuthenticationFilter(JwtAuthenticationConfig config) {
        this.config = config;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain)
            throws ServletException, IOException {
        String token = req.getHeader(config.getHeader());
        System.out.println("fuq filter");
        System.out.println(token);
        if (token != null && token.startsWith(config.getPrefix() + " ")) {
            try {
                token = token.replace(config.getPrefix() + " ", "");
                System.out.println(token);
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                if (decodedToken != null) {
                    System.out.println("Decodeed success !!!");
                    String uid = decodedToken.getUid();
                    String email = decodedToken.getEmail();
                    List<String> authorities = new ArrayList<>();
                    authorities.add("user");
                    authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, "jwt", authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    System.out.println("Authenicated : " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
                    System.out.println(SecurityContextHolder.getContext().getAuthentication());
                }
//            try {
//                Claims claims = Jwts.parser()
//                        .setSigningKey(config.getSecret().getBytes())
//                        .parseClaimsJws(token)
//                        .getBody();
//                String username = claims.getSubject();
//                @SuppressWarnings("unchecked")
//                List<String> authorities = claims.get("authorities", List.class);
//                if (username != null) {
//                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
//                            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//                    SecurityContextHolder.getContext().setAuthentication(auth);
//                }
//            } catch (Exception ignore) {
//                SecurityContextHolder.clearContext();
//            }
            } catch (FirebaseAuthException ex) {
                System.out.println("Firebase JWT Authen fail !");
                SecurityContextHolder.clearContext();
                rsp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                HashMap<String, String> message = new HashMap<>();
                message.put("response", "Unauthorize error");
                rsp.getOutputStream().write(new ObjectMapper().writeValueAsBytes(message));
                //rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                Logger.getLogger(JwtTokenAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        filterChain.doFilter(req, rsp);
    }
}
