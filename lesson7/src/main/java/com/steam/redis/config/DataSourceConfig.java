package com.steam.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean(name = "mysqlDataSource")
    @Qualifier("mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("mysqlDataSource") DataSource dataSource) {
        logger.info("mysqlJdbcTemplate... successful" + dataSource.toString());
        return new JdbcTemplate(dataSource);
    }
}
