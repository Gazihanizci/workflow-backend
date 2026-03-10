package com.example.workflowengine.security;

import com.example.workflowengine.entity.Kullanici;
import com.example.workflowengine.repository.KullaniciRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final KullaniciRepository kullaniciRepository;

    public CustomUserDetailsService(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Kullanici kullanici = kullaniciRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı"));

        return new User(
                kullanici.getEmail(),
                kullanici.getParolaHash(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}