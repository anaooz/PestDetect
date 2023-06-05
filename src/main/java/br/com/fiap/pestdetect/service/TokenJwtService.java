package br.com.fiap.pestdetect.service;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.pestdetect.models.Credencial;
import br.com.fiap.pestdetect.models.JwtToken;
import br.com.fiap.pestdetect.models.Usuario;
import br.com.fiap.pestdetect.repositories.UsuarioRepository;
import jakarta.validation.Valid;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.*;

@Service
public class TokenJwtService {
    
    @Value("${jwt.secret}")
    String secret;

    @Autowired
    UsuarioRepository repository;

    public JwtToken generateToken(@Valid Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
                    .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                    .withSubject(credencial.email())
                    .withIssuer("PestDetect")
                    .sign(alg);
        
        return new JwtToken(token);
    }

    public Usuario validate(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                    .withIssuer("PestDetect")
                    .build()
                    .verify(token)
                    .getSubject();

        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Token inválido"));
    }
}
