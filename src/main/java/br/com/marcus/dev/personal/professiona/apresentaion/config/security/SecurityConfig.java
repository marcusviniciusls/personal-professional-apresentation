package br.com.marcus.dev.personal.professiona.apresentaion.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**"
    };

    private static final String[] PUBLIC_MATCHERS_GET = {

    };

    @Override
    public void configure (HttpSecurity httpSecurity) throws Exception{
        if (Arrays.asList(env.getActiveProfiles()).contains("dev")){
            httpSecurity.headers().frameOptions().disable();
        }
        httpSecurity.cors().and().csrf().disable();
        httpSecurity.authorizeRequests().
                antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .anyRequest().authenticated();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfiguration(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}