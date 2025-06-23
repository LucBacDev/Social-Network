//package com.doan.MyWechat.com.Conf;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConf extends WebSecurityConfigurerAdapter {
//	
//	
//		protected void configure(final HttpSecurity http) throws Exception {
//			
//			http.csrf().disable()
//            .authorizeRequests()
//                .antMatchers("/css/**", "/js/**", "/images/**", "/img/**", "/login", "/register").permitAll() // Cho phép truy cập tài nguyên tĩnh và trang đăng nhập/đăng ký
//                .anyRequest().authenticated() // Yêu cầu xác thực cho các request khác
//            .and()
//            .formLogin()
//                .loginPage("/login").loginProcessingUrl("/login") // Trang đăng nhập
//                .defaultSuccessUrl("/home") // Trang chủ sau khi đăng nhập thành công
//                .permitAll() // Cho phép tất cả truy cập trang đăng nhập
//            .and()
//            .logout()
//                .logoutUrl("/logout") // URL đăng xuất
//                .logoutSuccessUrl("/login"); // URL sau khi đăng xuất
//		}
//}
