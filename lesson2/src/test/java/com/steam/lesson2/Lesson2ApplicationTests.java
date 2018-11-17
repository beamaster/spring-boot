package com.steam.lesson2;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Lesson2ApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Autowired
    @Qualifier("localTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void encryptProperties() {
        String name = stringEncryptor.encrypt("your actual username");
        String password = stringEncryptor.encrypt("your actual password");
        String url = stringEncryptor.encrypt("your actual jdbcUrl");
        System.out.println(name + "");
        System.out.println(password + "");
        System.out.println(url + "");

        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
        Assert.assertTrue(url.length() > 0);

    }


    @Test
    public void c3p0ConnectTest() {
        String addressName = "成都";
        String sql = "select id,orders,full_name,name,tree_path from xx_area where version = 0 and full_name like CONCAT('%', ?, '%')";

        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, addressName);

        if (list.size()>0){
            String address = "";
            for (Map<String, Object> map : list) {
                address = map.get("full_name").toString();
                System.out.println(addressName+":::" + address);
            }
        }
    }

}
