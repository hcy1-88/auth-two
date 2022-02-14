package com.hcy;

import com.hcy.mapper.CredentialsMapper;
import com.hcy.pojo.Credentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description：
 * Author: 黄成勇
 * Date:  2022/2/14 18:14
 */
@SpringBootTest
public class SqlTest {
    @Autowired
    private CredentialsMapper credentialsMapper;
    @Test
    public void test(){
        Credentials oauth_admin = credentialsMapper.findCredentialsByName("oauth_admin");
        System.out.println(oauth_admin);
        oauth_admin.getAuthorities().forEach(System.out::println);
    }
}
