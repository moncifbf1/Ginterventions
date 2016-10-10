package org.ocp;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception{
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username as principal, password as credentials, true from users where username=? and actived=1")
			.authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
			.rolePrefix("ROLE_");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {	        
		 http  
		 	.csrf().disable()
			.authorizeRequests()
				.antMatchers("/angular/**","/img/**","/css/**","/js/**","/fonts/**","/changeStatus/{code_user}/{token}").permitAll()
				.antMatchers("/EspaceAdministrateur/**").access("hasRole('ROLE_ADMINISTRATEUR')")
				.antMatchers("/EspaceResponsable/**").access("hasRole('ROLE_RESPONSABLE')")
				.antMatchers("/EspaceUtilisateur/**").access("hasRole('ROLE_UTILISATEUR')")
				.antMatchers("/EspaceOperateur/**").access("hasRole('ROLE_OPERATEUR')")
				.antMatchers(HttpMethod.GET, "/interventions/**").permitAll()
				.antMatchers(HttpMethod.GET, "/findLog").permitAll()
				.antMatchers(HttpMethod.PUT, "/addLog").permitAll()
				.antMatchers(HttpMethod.PUT, "/intervenir").permitAll()
				.antMatchers(HttpMethod.PUT, "/terminer").permitAll()
				.anyRequest().authenticated()
			.and().formLogin()
				.loginPage("/login")
				.usernameParameter("username").passwordParameter("password").successHandler(customSuccessHandler).permitAll()
			.and().logout()
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID")
				.logoutUrl("/logout")
				.invalidateHttpSession(true);	
	}
}
