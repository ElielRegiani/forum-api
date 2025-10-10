package br.com.forum_api.forum_api.config

import br.com.forum_api.forum_api.security.JWTAuthenticationFilter
import br.com.forum_api.forum_api.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfiguration(
    private val jwtUtil: JWTUtils,
    private val userDetailsService: UserDetailsService
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        authConfig: AuthenticationConfiguration
    ): SecurityFilterChain {

        val authenticationManager = authConfig.authenticationManager

        http
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(HttpMethod.POST, "/login")?.permitAll()
                    ?.anyRequest()?.authenticated()
            }
            .addFilterBefore(
                JWTLoginFilter(authManager = authenticationManager, jwtUtil = jwtUtil),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(
                JWTAuthenticationFilter(jwtUtil),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .csrf { csrf -> csrf.disable() }

        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager
    }
}
