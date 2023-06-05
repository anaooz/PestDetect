package br.com.fiap.pestdetect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import br.com.fiap.pestdetect.repositories.ContaRepository;

@Service
public class AuthenticationService implements UserDetailsService{
    
    @Autowired
    ContaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
