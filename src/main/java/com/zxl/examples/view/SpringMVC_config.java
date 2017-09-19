package com.zxl.examples.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Created by Administrator on 2017/7/28.
 */
@Configuration
public class SpringMVC_config {
    @Bean(name="localeResolver")
    public LocaleResolver localeResolverBean() {
        return new SessionLocaleResolver();
    }
}
