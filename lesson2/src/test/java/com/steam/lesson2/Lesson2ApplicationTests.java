package com.steam.lesson2;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Lesson2ApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void encryptProperties() {
        System.out.println(stringEncryptor.encrypt("your actual username"));
        System.out.println(stringEncryptor.encrypt("your actual password"));
        System.out.println(stringEncryptor.encrypt("your actual jdbcUrl"));
    }


    @Test
    public void contextLoads() {
    }

}
