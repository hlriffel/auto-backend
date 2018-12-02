package br.edu.senac.auto.security.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class Filters {

    private final LoginFilter loginFilter;
    private final MainFilter mainFilter;

    @Autowired
    public Filters(LoginFilter loginFilter, MainFilter mainFilter) {
        this.loginFilter = loginFilter;
        this.mainFilter = mainFilter;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> loginRegistrationBean() {
        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(loginFilter);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/login/*"));

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<MainFilter> mainRegistrationBean() {
        FilterRegistrationBean<MainFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(mainFilter);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/rest/*"));

        return filterRegistrationBean;
    }
}
