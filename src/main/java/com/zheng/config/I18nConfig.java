package com.zheng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * Created by zhenglian on 2016/10/12.
 */
@Configuration
public class I18nConfig extends WebMvcConfigurationSupport {

    /**
     * session区域解析器
     * @return
     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver resolver = new SessionLocaleResolver();
//        //这里通过设置China.US可以进行中英文转化
//        resolver.setDefaultLocale(Locale.US);
//
//        return resolver;
//    }


    /**
     * cookie区域解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
        //设置默认区域,
        slr.setDefaultLocale(Locale.CHINA);
        slr.setCookieMaxAge(3600);//设置cookie有效期.
        return slr;
    }

//    @Bean
//    public LocaleResolver localeResolver() {
//        FixedLocaleResolver slr = new FixedLocaleResolver ();
//        //设置默认区域,
//        slr.setDefaultLocale(Locale.US);
//        return slr;
//    }


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 设置请求地址的参数,默认为：locale
//          lci.setParamName(LocaleChangeInterceptor.DEFAULT_PARAM_NAME);
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
