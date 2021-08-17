package com.ifi.fresher_test.ifi_fresher_test.config;

import com.ifi.fresher_test.ifi_fresher_test.service.AccountDetailsService;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@CrossOrigin
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public final String HOST_NAME = "http://localhost:4242";

    AccountDetailsService accountDetailsService;

    @Autowired
    public void setAccountDetailsService(AccountDetailsService accountDetailsService) {
        this.accountDetailsService = accountDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HOST_NAME+"/", HOST_NAME+"/login", HOST_NAME+"/registration").permitAll()
                .antMatchers(HOST_NAME+"/contestant/").hasAnyAuthority(MessageResource.CONTESTANT)
                .antMatchers(HOST_NAME+"/contributor/").hasAnyAuthority(MessageResource.CONTRIBUTOR)
                .and().formLogin();
    }
}
