package com.luv2code.ecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // protect /api/orders endpoint
        http.authorizeRequests()
                .antMatchers(basePath + "/orders/**")
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();

        // add CORS filters
        http.cors();

        // force a non-empty response body for 401
        Okta.configureResourceServer401ResponseBody(http);
    }
}
