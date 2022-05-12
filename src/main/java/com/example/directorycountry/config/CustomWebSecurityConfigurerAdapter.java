package com.example.directorycountry.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE)
class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public CustomWebSecurityConfigurerAdapter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT t.user_name, t.password, t.is_active FROM users t WHERE t.user_name = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.user_name, ur.name_role" +
                                "FROM user_role ur" +
                                "INNER JOIN users u " +
                                "on ur.user_id = u.id" +
                                "INNER JOIN roles r" +
                                "on ur.role_id = r.id" +
                                "WHERE u.user_name = ? AND u.is_active = 1");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "api/v1/user/register").permitAll()
                .antMatchers(HttpMethod.POST, "api/v1/user/auto").permitAll()
                .antMatchers(HttpMethod.GET, "api/v1/user/{id}").hasRole("USER")
                .antMatchers(HttpMethod.POST,"api/v1/country/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"api/v1/country/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"api/v1/country/report-endpoint").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"api/v1/country/all").hasRole("USER")
                .antMatchers(HttpMethod.POST,"api/v1/country/search").hasRole("USER")
                .antMatchers(HttpMethod.POST,"api/v1/country/add-photo").hasRole("USER")
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

