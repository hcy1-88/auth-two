package com.hcy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;    //认证管理器
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;//密码加密方式
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource oauthDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(oauthDataSource());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(oauthDataSource());
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(oauthDataSource());
    }

    // 增加授权码模式的支持
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(oauthDataSource());
    }

    // 配置客户端详情服务的
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 第一种，内存方式，验证客户端信息
        // 定义1个客户端认证，为了方便，这里构造了一个客户端放在内存里，就类似于 没有数据库用户，就在内存里新建用户
        // 对于授权服务器来说，客户端就像用户一样，客户端使用第三方授权服务器 也是要注册的，也要有id和密码
//        clients.inMemory()
//                // 客户端 id， 就是第三方应用的id
//                .withClient("client")
//                // 客户端的认证密钥
//                .secret(passwordEncoder.encode("1234567"))
//                // 重定向地址，用户完成授权登录后，客户端重定向的地址，通常是你登录的第三方网站的地址
//                .redirectUris("https://www.baidu.com")
//                // 授权范围，用户的信息、头像等
//                .scopes("all")
//                // 授权类型：授权码模式，授权类型可以同时有多个
//                .authorizedGrantTypes("authorization_code");
        // 第二种，基于数据库验证客户端
        clients.withClientDetails(jdbcClientDetailsService());
    }

    // 配置令牌的安全约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients();
        oauthServer.checkTokenAccess("permitAll()");
        oauthServer
                .checkTokenAccess("isAuthenticated()") // 开启/oauth/check_token 验证端口认证权限访问
                .allowFormAuthenticationForClients()// 允许表单认证
                .passwordEncoder(passwordEncoder);   // 配置BCrypt加密
    }

    // 配置令牌的访问端点和服务的
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .pathMapping("/oauth/confirm_access","/toAuthorize")
                .approvalStore(approvalStore())
                .authenticationManager(authenticationManager)// 开启密码验证
                .authorizationCodeServices(authorizationCodeServices())
                .tokenStore(tokenStore())
                .setClientDetailsService(jdbcClientDetailsService());
    }

}