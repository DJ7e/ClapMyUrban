//package com.dbms.UrbanClaps.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password ,'true' as enabled from User where username=?")
//                .authoritiesByUsernameQuery("select username, authority  from User where username=?")
//        ;
//    }
//
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests((requests) -> {
//////                            requests.requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/icons/**", "/webjars/**", "/assests/**", "/index.html", "/index", "/", "/logout", "/logIn", "/signUp").permitAll();
//////                            requests.requestMatchers("/error").permitAll();
//////                            requests.requestMatchers("/customer/**", "/invoice/**", "/complaint/**", "/admin/**").hasAnyAuthority("SA", "A");
//////                            requests.requestMatchers("/employee/**").hasAuthority("SA");
//////                            requests.requestMatchers("/warehouse/**").hasAnyAuthority("SA", "WM", "A");
//////                            requests.requestMatchers("/wm/**").hasAnyAuthority("WM");
//////                            requests.requestMatchers("/dm/**").hasAnyAuthority("D");
////                            requests.anyRequest().authenticated();
////                        }
////                );
////                .formLogin((form) -> form
////                        .loginPage("/login")
////                        .loginProcessingUrl("/login")
////                        .successForwardUrl("/login_success_handler")
////                        .failureForwardUrl("/login_failure_handler")
////                        .permitAll()
////                )
////                .logout((logout) -> logout
////                        .logoutUrl("/logout")
////                        .logoutSuccessUrl("/login")
////                        .permitAll());
//
//
//        return http.build();
//
//
//    }
//}