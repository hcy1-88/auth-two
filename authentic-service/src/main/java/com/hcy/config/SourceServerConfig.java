package com.hcy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class SourceServerConfig extends ResourceServerConfigurerAdapter {
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource oauthDataSource(){return DataSourceBuilder.create().build();}
    @Autowired
    private DataSource oauthDataSource;  // 如果是分开的话，数据源要再@Bean一遍，如上所示

    // 资源服务器的配置
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http.authorizeRequests()
                // 以下代码的意思：所有请求必须被认证，其中有 /user/**，没有包括的就不认证
                .anyRequest().authenticated()
                .and()
                .requestMatchers()
                // 第三方应用必须携带令牌，被认证后放行 /user/** 下的资源，这里的/user/**就是一个 controller
                .antMatchers("/user/**");
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        TokenStore tokenStore=new JdbcTokenStore(oauthDataSource);
        // 注意，这里的 resourceId 必须与数据库中的 client-Details表的一致
        resources.resourceId("oauth2").tokenStore(tokenStore);
    }
}