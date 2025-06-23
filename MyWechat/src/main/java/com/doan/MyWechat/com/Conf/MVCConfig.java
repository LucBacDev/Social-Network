package com.doan.MyWechat.com.Conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration

public class MVCConfig implements WebMvcConfigurer {
	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("homepage");
//	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//class path tuog duong src/main/resource
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
		registry.addResourceHandler("/images/**").addResourceLocations("file:"+"E:/ProjectDoAn/DoAn/images/");
	}
	
	@Bean
	public ViewResolver viewReslover() {
		//Khai bao phan tu con cua view Reslover
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		//thiet lap view engine
		bean.setViewClass(JstlView.class);
		//st duong dan chua view 
		bean.setPrefix("/WEB-INF/jsps/");
		bean.setSuffix(".jsp");
		return bean;
	}
}
