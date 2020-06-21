package com.work.auth;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ZhaiYunpeng
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.work.auth.dao")
@EnableScheduling
public class AuthApplication implements WebMvcConfigurer {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(AuthApplication.class);
        //项目启动
        ConfigurableApplicationContext context = app.run(args);
        //获取启动环境配置，用于打印使用
        Environment environment = context.getEnvironment();
        log.info("\n----------------------------------------\n\t"
                        + "Application '{}' is running!Access URLS:\n\t"
                        + "Local:\t\thttp://localhost:{}\n\t"
                        + "External:\thttp://{}:{}\n\t"
                        + "Profile(s):\t{}"
                        + "\n----------------------------------------\n\t",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"),
                environment.getActiveProfiles()
        );
    }

    /**
     * 添加SwaggerAPI文档接口，需要实现WebMvcConfigurer，不能继承，不然yml文件里的部分配置无效
     *
     * @param registry 注册资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
