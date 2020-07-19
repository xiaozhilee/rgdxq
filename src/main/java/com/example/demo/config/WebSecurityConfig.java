package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/urls","/swagger-ui.html", "/swagger-resources/**",
                        "/images/**", "/webjars/**", "/v2/api-docs", "/configuration/ui",
                        "/configuration/security","/JHtest","/urlcode","/devices","/**",
                        "/JHtest/**","/error","/regist/**","/page/**","/js/**","/css/**",
                        "/html/**","/expense","/expenses","urls","/test1","/surl/**","/MyWebsocket/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/page/login").loginProcessingUrl("/Login")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .permitAll();

            //允许跨域请求
            // by default uses a Bean by the name of corsConfigurationSource(官方说明，使下面配置的bean生效)
            httpSecurity.cors(Customizer.withDefaults());

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");//修改为添加而不是设置，* 最好改为实际的需要，我这是非生产配置，所以粗暴了一点
        configuration.addAllowedMethod("*");//修改为添加而不是设置
        configuration.addAllowedHeader("*");//这里很重要，起码需要允许 Access-Control-Allow-Origin
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
