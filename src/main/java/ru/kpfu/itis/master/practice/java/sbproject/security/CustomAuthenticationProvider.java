//package ru.kpfu.itis.master.practice.java.sbproject.security;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//import ru.kpfu.itis.master.practice.java.sbproject.entities.User;
//import ru.kpfu.itis.master.practice.java.sbproject.services.UserServiceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserServiceImpl userService;
//
//    public CustomAuthenticationProvider(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String email = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        User user = userService.findUserByEmail(email);
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getRole().toString())); // description is a string
//
//        return new UsernamePasswordAuthenticationToken(email, password, authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
