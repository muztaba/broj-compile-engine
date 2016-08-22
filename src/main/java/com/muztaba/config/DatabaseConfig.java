package com.muztaba.config;

import com.muztaba.entity.Problem;
import com.muztaba.entity.Submission;
import com.muztaba.entity.User;
import com.muztaba.entity.Verdict;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by sazzad on 9/7/15
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Autowired
    private ApplicationContext appContext;


    @Bean(name = "DataSource")
    public HikariDataSource dataSourceWinMac() {
        return getDataSource("127.0.0.1", "root", "seal", 3306,"broj");
    }

    private HikariDataSource getDataSource(String serverName, String user, String password, int port, String dbName) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        dataSource.addDataSourceProperty("databaseName", dbName);
        dataSource.addDataSourceProperty("portNumber", port);
        dataSource.addDataSourceProperty("serverName", serverName);
        dataSource.addDataSourceProperty("user", user);
        dataSource.addDataSourceProperty("password", password);
        return dataSource;
    }


    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(hibernate5SessionFactoryBean().getObject());
        return manager;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean hibernate5SessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource((DataSource) appContext.getBean("DataSource"));
        localSessionFactoryBean.setAnnotatedClasses(
                User.class,
                Problem.class,
                Submission.class,
                Verdict.class
        );

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        //properties.put("hibernate.current_session_context_class","thread");
        properties.put("hibernate.hbm2ddl.auto", "update");

        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }
}
