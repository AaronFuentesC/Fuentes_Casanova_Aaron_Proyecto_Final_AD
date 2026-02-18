package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.controller;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.LoginRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.RegisterRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers.UsuarioRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Usuario;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor    //Crea un constructor que Inyecta todos los objetos "final"
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Autenticar al usuario con email y password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
            );

            // Si las credenciales son correctas, generar token
            String token = jwtService.generateToken(authentication);
            return ResponseEntity.ok(Map.of("token", token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales incorrectas"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error en el servidor"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // Verificar si el email ya existe
            if (usuarioRepository.findByEmail(registerRequest.email()).isPresent()) {
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "Ya estás registrado en la aplicación");
                response.put("email", registerRequest.email());

                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
            if(!registerRequest.password().equals(registerRequest.password2())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("mensaje", "Las contraseñas no coinciden"));
            }

            // Crear nuevo usuario
            Usuario usuario = new Usuario();
            usuario.setEmail(registerRequest.email());
            usuario.setPassword(passwordEncoder.encode(registerRequest.password()));  // Cifrar password
            usuario.setRoles("ROLE_USER");  // Rol por defecto

            usuarioRepository.save(usuario);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje", "Usuario registrado correctamente"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al registrar usuario"));
        }
    }


}

