package com.steam.lesson2;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.steam.lesson2.model.ClientPowerModel;
import com.steam.lesson2.service.CommonService;
import com.steam.lesson2.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

@EnableScheduling
@Controller
@SpringBootApplication
public class Lesson2Application {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "localSource")
    @ConfigurationProperties(prefix = "c3p0")
    public DataSource mysql127Source(){
        return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    }

    @Bean(name = "localTemplate")
    public JdbcTemplate mysql127Template(@Qualifier("localSource") DataSource source){
        return new JdbcTemplate(source);
    }
    public static void main(String[] args) {
        SpringApplication.run(Lesson2Application.class, args);
        String path1 = Lesson2Application.class.getResource("/static/file/201117.xls").getFile();
        System.out.println(",,,,"+path1);
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public String index(){
        return "morning,"+new Date();
    }


    @Autowired
    CommonService commonService;

    @Scheduled(fixedRate = 10000L)
    public void execTask(){
        String path = "";
        try {
            path   = ResourceUtils.getFile("classpath:static/file/201117.xls").getPath();
            System.out.println(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<ClientPowerModel> list = ExcelUtil.readExcelAsModel(path,0,0,1);
        if(list.size() >0){
            commonService.avoidInsert(list);
        }
    }






















}
