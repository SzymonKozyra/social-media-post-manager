package com.example.productcataloglab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;



@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {
    @Value("${db.driver}")
    private String dbDriver;
    @Value("${db.ip}")
    private String dbIp;
    @Value("${db.port}")
    private String dbPort;
    @Value("${db.name}")
    private String dbName;
    @Value("${db.protocol}")
    private String dbProtocol;
    @Value("${db.username}")
    private String dbUsername;
    @Value("${db.password}")
    private String dbPassword;

    private final Environment environment;

    public PersistenceJPAConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSourceDev = new DriverManagerDataSource();
        dataSourceDev.setDriverClassName(dbDriver);
        String dbUrl = dbProtocol + "://" + dbIp + ":" + dbPort + "/" + dbName;
        dataSourceDev.setUrl(dbUrl);
        dataSourceDev.setUsername(dbUsername);
        dataSourceDev.setPassword(dbPassword);
        return dataSourceDev;
    }
}
