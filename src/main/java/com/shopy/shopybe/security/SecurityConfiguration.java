package com.shopy.shopybe.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    private final JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        final var daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(jwtUserDetailsService);

        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final var usernamePasswordFilter = new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), secret, issuer);
        final var authorizationFilter = new JwtAuthorizationFilter(secret);

        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/sign-up")
                    .permitAll()
                .antMatchers("/api/v1/dummy")
                    .hasAuthority(ApplicationRole.ROLE_ADMIN.name())
                .anyRequest()
                    .authenticated()
                .and()
                .addFilterAt(usernamePasswordFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(authorizationFilter, BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
