package com.ximena.biblioteca_api.auth;

import com.ximena.biblioteca_api.model.Rol;
import com.ximena.biblioteca_api.model.Usuario;
import com.ximena.biblioteca_api.repository.UsuarioRepository;
import com.ximena.biblioteca_api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthResponse login(LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));
        Usuario usuario = usuarioRepository
                .findByEmail(request.getEmail())
                .orElseThrow();
        return new AuthResponse(jwtService.generarToken(usuario));
    }

    public AuthResponse registro(LoginRequest request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(Rol.USER);
        usuarioRepository.save(usuario);
        return new AuthResponse(jwtService.generarToken(usuario));
    }
}
