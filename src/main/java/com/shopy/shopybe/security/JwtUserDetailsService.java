package com.shopy.shopybe.security;

import com.shopy.shopybe.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final var user = applicationUserRepository.findByEmailAddress(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        final var authorities = user.getRole().getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        return JwtUserDetails.builder()
                .authorities(authorities)
                .username(email)
                .password(user.getPassword())
                .enabled(user.isEnabled())
                .build();
    }
}
