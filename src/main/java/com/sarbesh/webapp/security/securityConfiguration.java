package com.sarbesh.webapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter{
	

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.headers().frameOptions().sameOrigin()
			.and()
			.authorizeRequests()
//				.anyRequest().permitAll()
				.antMatchers("/", "/register", "/h2-console/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
//				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/logout-success")
				.and().csrf().disable();
	}
	
}
