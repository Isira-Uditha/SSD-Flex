package com.example.Flexserver.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcDatasourceConfiguration {

    @Value("${ems.datasource.url}")
    private String url;

    @Value("${ems.datasource.username}")
    private String username;

    @Value("${ems.datasource.password}")
    private String password;

    @Value("${ems.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${ems.datasource.max-pool-size}")
    private int maxPoolSize;

    @Bean(name = "mysqlDataSource")
    @Primary
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        hikariConfig.setMinimumIdle(2);
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "namedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}