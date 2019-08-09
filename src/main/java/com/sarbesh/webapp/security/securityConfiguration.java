package com.sarbesh.webapp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sarbesh.webapp.config.JwtAuthenticationFilter;
import com.sarbesh.webapp.repository.EmployeeRepository;
import com.sarbesh.webapp.services.EmployeeDetailsService;

@Configuration
@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter{
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
//	@Autowired
	private EmployeeDetailsService userDetailsService;
	private EmployeeRepository employeeRepository;
	
	public securityConfiguration(EmployeeDetailsService userDetailsService, EmployeeRepository employeeRepository) {
		super();
		this.userDetailsService = userDetailsService;
		this.employeeRepository = employeeRepository;
	}
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) {
	        auth.authenticationProvider(authProvide());
	    }

	@Bean
	public AuthenticationProvider authProvide() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//			.headers().frameOptions().sameOrigin()
			.and()
			.addFilter(new JwtAuthenticationFilter(authenticationManager()))
			.authorizeRequests()
				.antMatchers("/index", "/api/register", "/h2-console/**", "/browser/**", "/login").permitAll()
//				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**", "/h2-console/**");
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
