//package ru.kpfu.itis.master.practice.java.sbproject.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomAuthenticationProvider authProvider;
//
//    public SpringSecurityConfig(CustomAuthenticationProvider authProvider) {
//        this.authProvider = authProvider;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/*", "/login", "/profile", "users-*",
//                            "/registration", "/filesStorage*").permitAll()
//                    .antMatchers("/users_list").hasAnyRole("ADMIN")
//                    .antMatchers("/123").hasAnyRole("USER")
//                    .antMatchers("/css/*", "/js/*", "/images/*").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .invalidateHttpSession(true)
//                    .clearAuthentication(true)
//                    .permitAll();
//    }
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authProvider);
//    }
//
//}
