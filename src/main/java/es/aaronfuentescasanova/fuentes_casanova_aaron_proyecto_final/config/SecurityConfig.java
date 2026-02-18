package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.config;


import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration          //Archivo de configuración
@EnableWebSecurity      //Configuración de Spring Security
@EnableMethodSecurity  // Permite usar @PreAuthorize en controladores
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF (no necesario en APIs REST con JWT)
                .cors(cors -> {}) // 👈 IMPORTANTE
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        // Equipos
                        .requestMatchers(HttpMethod.PUT, "/equipos/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/equipos/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/equipos/**").hasRole("ADMIN")

                        // Jugadores
                        .requestMatchers(HttpMethod.PUT, "/jugadores/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/jugadores/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/jugadores/**").hasRole("ADMIN")


                        //Partidos
                        .requestMatchers(HttpMethod.POST, "/partidos/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/partidos/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/partidos/**").hasRole("ADMIN")

                        //Torneos
                        .requestMatchers(HttpMethod.POST, "/torneos/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/torneos/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/torneos/**").hasRole("ADMIN")


                        .anyRequest().authenticated()


                )

                // Configurar validación automática de tokens JWT
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .decoder(jwtDecoder())  // Cómo validar el token
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())  // Cómo extraer roles
                        )
                )

                // Sin sesiones (stateless) - cada petición debe llevar su token
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Configura cómo validar los tokens JWT con la clave secreta
        return NimbusJwtDecoder.withSecretKey(jwtService.getSecretKey()).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Configura cómo extraer los roles del token
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthoritiesClaimName("roles");  // Buscar en claim "roles"
        authoritiesConverter.setAuthorityPrefix("");             // Sin prefijo adicional

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return jwtConverter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt para cifrar contraseñas en la base de datos
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        // Necesario para validar email/password en el login
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}