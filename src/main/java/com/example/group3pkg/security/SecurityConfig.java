package com.example.group3pkg.security;



import com.example.group3pkg.services.CustomSuccessHandler;
import com.example.group3pkg.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(c -> c.disable())

                .authorizeHttpRequests(request -> request.requestMatchers("/admin-page","/css/**")
                        .hasAuthority("ADMIN").requestMatchers("/user-page","/css/**").hasAuthority("USER")
                        .requestMatchers("/registration", "/css/**","/api/contacts","/api/events","/api/events/{id}","/api/venues","/api/venues/{id}","/api/tasks","/api/login","/login","/css/**","/Contact").permitAll()
                        .requestMatchers("/events", "/css/**").permitAll()
                        .requestMatchers("/contacts/create", "/css/**").permitAll()
                        .requestMatchers("/events/create","/events/user","/events/delete","/events/edit","/events/update","/contacts/create" ,"/css/**").permitAll()
                        .requestMatchers("/contacts","/events/user","/contacts/delete","/contacts/edit","/contacts/update","/contacts/create" ,"/css/**").permitAll()
                        .requestMatchers("/Venue","/venue/delete","/venue/edit","/venue/update","/venue/create" ,"/css/**").permitAll()
                        .requestMatchers("/Task","/Tasks/delete","/tasks/edit","/tasks/update","/Tasks/create" ,"/css/**").permitAll()
                        .requestMatchers("/EventType","/EventType/delete","/EventType/edit","/EventType/update","/EventType/create","/EventType/user" ,"/css/**").permitAll()
                        .requestMatchers( "/styles/**","/js/**","/Img_SVG/**","/fonts/**","/css/**","/assets/**","/forms/**").permitAll()
                        .anyRequest().authenticated())


                .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler).permitAll())

                .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout").permitAll());

        return http.build();

    }

    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

}