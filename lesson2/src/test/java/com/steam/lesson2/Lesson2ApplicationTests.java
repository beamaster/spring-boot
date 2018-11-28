package com.steam.lesson2;

import com.steam.lesson2.model.ClientPowerModel;
import com.steam.lesson2.service.CommonService;
import com.steam.lesson2.util.ExcelUtil;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    @Autowired
    CommonService commonService;

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

    @Test
    public void avoidInsertTest() {

        ClassPathResource p1 = new ClassPathResource("application.properties");

        ClassPathResource p2 = new ClassPathResource("static/file/201117.xls");

        String path = "";
        try {
            System.out.println("p1:"+ p1.getFile().length());
            System.out.println("p1:"+ p1.getFile().getPath());
            System.out.println("p2:"+ p2.getFile().length());
            System.out.println("p2:"+ p2.getFile().getPath());
            path   = ResourceUtils.getFile("classpath:static/file/201117.xls").getPath();
            System.out.println(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        List<ClientPowerModel> list = ExcelUtil.readExcelAsModel(path,0,0,1);
        if(list.size() >0){
            commonService.avoidInsert(list);
        }
    }

}
