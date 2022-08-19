package com.s3.t.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private  JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private  JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                .cors()
                .and()
                // don't authenticate this particular request
                .authorizeHttpRequests()
                .antMatchers(publicEndpoint).permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/register").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/login").permitAll()
                
                .antMatchers(HttpMethod.POST,"/image/upload").permitAll()
                .antMatchers(HttpMethod.POST,"/location/save").permitAll()
                //Property
                .antMatchers(HttpMethod.POST,"/property/add").permitAll()
                //Location
                .antMatchers(HttpMethod.GET,"/location/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/location/filter").permitAll()
                .antMatchers(HttpMethod.DELETE,"/location/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/location/{id}").permitAll()

                // all other requests need to be authenticated
                .anyRequest().authenticated().and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    private static final String[] publicEndpoint = {
            "/swagger-resources/**",
            "/swagger-ui/**", "/v2/api-docs",
            "/v3/api-docs",
            "/api/docs",
            "/api/docs/**",
            "/api/docs/swagger-ui",
            "/swagger-ui.html",
            "/**/swagger-ui/**",
            "/swagger-ui"
    };

}