package org.honeyrock.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.Setter;
import lombok.extern.java.Log;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Log
public class SpringSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
/*	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**", "/**");
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		log.info("security");
		http.authorizeRequests()
				.antMatchers("/index","/search","/autocomplete","/**/list","/**/read").permitAll() // 권한없이 들어갈 페이지
				.antMatchers("/**/register","/**/modify","/**/delete","/mypage","/simple", "/map").hasRole("MEMBER") //권한이 필요한 페이지 추가
				.and()
			.formLogin()
				.loginPage("/login/customLogin").permitAll()
				.successHandler(successHandler())
				.failureHandler(failHandler())
				//.defaultSuccessUrl("/index") // access denied 걸리면  로그인페이지 갈거라고 선언
				.and()
			.logout()
				/*.logoutUrl("/login/customLogout")*/
				.logoutRequestMatcher(new AntPathRequestMatcher("/login/customLogout")) //이걸로 하면 customlogout페이지 없이 가능
				.logoutSuccessHandler(logoutHandler());
		http.rememberMe().key("remember")
			.userDetailsService(userDetailsService())
			.tokenRepository(getJDBCReopsitory())
			.tokenValiditySeconds(60*60*24*15);
	}
	
	private PersistentTokenRepository getJDBCReopsitory() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService UserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler failHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public LogoutSuccessHandler logoutHandler() {
		return new CustomLoginSuccessHandler();
	}
	
}
