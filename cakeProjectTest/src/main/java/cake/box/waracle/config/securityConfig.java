package cake.box.waracle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cake.box.waracle.filter.securityFilter;

@EnableWebSecurity
@Configuration
public class securityConfig  extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService service;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	securityFilter sfilter;
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("1");
		auth.userDetailsService(service).passwordEncoder(passwordEncoder);
	}
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().antMatchers("/h2-console/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/","/save","/login").permitAll()
	//	.antMatchers("/cakes").access("hasRole('ADMIN')")
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(sfilter, UsernamePasswordAuthenticationFilter.class);
		
	}
}
