package com.biat.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(request -> {
                var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                corsConfig.setAllowedOrigins(java.util.List.of("http://localhost:4200")); // Angular app URL
                corsConfig.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                corsConfig.setAllowedHeaders(java.util.List.of("*"));
                corsConfig.setAllowCredentials(true);
                return corsConfig;
            }))
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Public endpoints (auth, registration)
                .requestMatchers("/api/auth/**", "/api/users/register").permitAll()

                // Full CRUD (authenticated) tables
                .requestMatchers(HttpMethod.GET, "/api/sc-detectfile/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/sc-detectfile").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/sc-detectfile/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/sc-detectfile/**").authenticated()

                .requestMatchers(HttpMethod.GET, "/api/sc-param-global/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/sc-param-global").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/sc-param-global/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/sc-param-global/**").authenticated()

                .requestMatchers(HttpMethod.GET, "/api/sc-mt-struct/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/sc-mt-struct").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/sc-mt-struct/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/sc-mt-struct/**").authenticated()

                .requestMatchers(HttpMethod.GET, "/api/sc-mapping-mt-to-mx/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/sc-mapping-mt-to-mx").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/sc-mapping-mt-to-mx/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/sc-mapping-mt-to-mx/**").authenticated()

                .requestMatchers(HttpMethod.GET, "/api/sc-mapping-mx-to-mt/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/sc-mapping-mx-to-mt").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/sc-mapping-mx-to-mt/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/sc-mapping-mx-to-mt/**").authenticated()

                .requestMatchers(HttpMethod.GET, "/api/sc-mapping-directory/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/sc-mapping-directory").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/sc-mapping-directory/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/sc-mapping-directory/**").authenticated()

                // Read-only (GET only) tables
                .requestMatchers(HttpMethod.GET, "/api/sc-mt-file/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/sc-mt-details/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/sc-ns-mt/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/sc-ns-mt-details/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/sc-mx-file/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/sc-mx-details/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/sc-ns-mx/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/sc-ns-mx-details/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/sc-mx-struct/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/sc-rdl/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/sc-conversion/**").authenticated()

                // User endpoints
                .requestMatchers(HttpMethod.PUT, "/api/users/update/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/users/delete/**").authenticated()
                //.requestMatchers(HttpMethod.GET, "/api/users/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/users/**").permitAll()



                // Admin-only access
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // All other requests authenticated
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
