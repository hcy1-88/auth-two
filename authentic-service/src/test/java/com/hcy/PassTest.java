package com.hcy;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description：
 * Author: 黄成勇
 * Date:  2022/2/14 21:09
 */
public class PassTest {
    // 测试加密
    @Test
    public void test01(){
        // 建议使用 BCryptPasswordEncoder 来加密，更安全
        PasswordEncoder pe = new BCryptPasswordEncoder();  // 加密器
        // 加密,每次加密，得到的随机数都不一样，加密后的密码就不一样
        //String pp = pe.encode("123");
        String mm = "$2a$10$.hBSnhoxMtptGUca5qV8.eei/50RShRq2U5R5Fy3sa7e57AP13Seq";
        //System.out.println(pp);
        boolean matches = pe.matches("123",mm);
        System.out.println(matches);  // true
    }
}
