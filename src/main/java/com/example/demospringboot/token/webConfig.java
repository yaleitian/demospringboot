package com.example.demospringboot.token;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class webConfig extends WebMvcConfigurerAdapter {

    /**
     * 注入拦截器，这里一定需要提前注入，否则拦截器中注入的对象将无法注入
     *
     * @return
     */
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，添加拦截路径和排除拦截路径 ，这里直接使用上面的方法直接获取注入的拦截器即可，否则将会造成拦截器中无法注入其他的对象
        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/test")
                .excludePathPatterns("/user/login");
    }
}