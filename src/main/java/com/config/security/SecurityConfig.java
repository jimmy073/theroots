package com.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomLoginSuccessHandler customLoginSuccessHandler;
		
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);  
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        
        .authorizeRequests()
        		.antMatchers("/index", "/","/user/registerUser",
        				"/user/saveUser","/js/**","/img/**","/css/**")
                .permitAll().antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .failureUrl("/login?error").successHandler(customLoginSuccessHandler)
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .and().logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();
    }
		
	

//@Bean
//@Override
//protected UserDetailsService userDetailsService() {
//	List<UserDetails> users = new ArrayList<>();
//	users.add(User.withDefaultPasswordEncoder().password("jimmy").username("kalisa")
//			.roles("USER").build());
//	return new InMemoryUserDetailsManager(users);
//}
	
	
}
