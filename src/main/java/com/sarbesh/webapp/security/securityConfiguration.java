package com.sarbesh.webapp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvide() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.headers().frameOptions().sameOrigin()
			.and()
			.authorizeRequests()
//				.anyRequest().permitAll()
				.antMatchers("/index", "/api/register", "/h2-console/**", "/browser/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/api/login").permitAll()
				.and()
			.logout()
				.permitAll()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
				.logoutSuccessUrl("/api/logout-success")
				.and().csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**");
	}
	
}
