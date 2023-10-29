//package com.dbms.UrbanClaps.config;
//
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * Spring Security configuration.
// *
// * @author Rob Winch
// * @author Vedran Pavic
// */
//@Configuration
//public class SecurityConfig {
//
//    // @formatter:off
//    @Bean
//    WebSecurityCustomizer ignoringCustomizer() {
//        return (web) -> web.ignoring().anyRequest();
//    }
//    // @formatter:on
//
//    // @formatter:off
//    // tag::config[]
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin((formLogin) -> formLogin.permitAll())
//                .build();
//    }
//    // end::config[]
//    // @formatter:on
//
//}