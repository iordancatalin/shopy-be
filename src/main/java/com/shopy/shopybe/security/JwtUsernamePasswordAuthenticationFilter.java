package com.shopy.shopybe.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopy.shopybe.model.SignInRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.shopy.shopybe.security.SecurityConstants.AUTHORIZATION_HEADER;
import static com.shopy.shopybe.security.SecurityConstants.TOKEN_PREFIX;

@Log4j2
@RequiredArgsConstructor
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final String secret;
    private final String issuer;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            final var signInRequest = new ObjectMapper().
                    readValue(request.getInputStream(), SignInRequest.class);

            Objects.requireNonNull(signInRequest.getUsername());
            Objects.requireNonNull(signInRequest.getPassword());

            final var authentication = new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword());

            return authenticationManager.authenticate(authentication);
        } catch (Exception e) {
            log.error(e);

            throw new BadCredentialsException("Bad Credentials", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {

        final var zoneId = ZoneId.systemDefault();
        final var expirationInstant = LocalDate.now().plusDays(3).atStartOfDay(zoneId).toInstant();

        final var auths = authResult.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        final var jwt = Jwts.builder()
                .setSubject(authResult.getName())
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(expirationInstant))
                .claim("auths", auths)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();

        response.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + jwt);
    }
}
