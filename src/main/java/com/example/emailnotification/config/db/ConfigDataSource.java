package com.example.emailnotification.config.db;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ConfigDataSource {

    @Value("${spring.datasource.url}")
    private  String url;
    @Value("${spring.datasource.username}")
    private  String username;
    @Value("${spring.datasource.password}")
    private  String password;
    @Value("${spring.datasource.driver-class-name}")
    private  String driverClassName;


    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private  int hikariMaximumPoolSize = 10;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private  int hikariMinimumIdle = 10;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private  long hikariConnectionTimeout;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private  long hikariIdleTimeout;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private  long hikariMaxLifeTime;



    @Bean
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig();

        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);

        config.setMaximumPoolSize(hikariMaximumPoolSize);
        config.setMinimumIdle(hikariMinimumIdle);
        config.setConnectionTimeout(hikariConnectionTimeout);
        config.setMaxLifetime(hikariMaxLifeTime);
        config.setIdleTimeout(hikariIdleTimeout);

        HikariDataSource dataSource = new HikariDataSource(config);

        return dataSource;

    }
}
