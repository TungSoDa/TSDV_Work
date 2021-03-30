package com.tsdv.demospringboot.SpringConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //nơi cấu hình cho toàn bộ ứng dụng
public class DatabaseConfig {
    @Value("${tungds.mysql.url}")
    String mysqlUrl;
    @Bean("mysqlConnector")
    DatabaseConnector mysqlConfigure() {
        DatabaseConnector mySqlConnector = new MySqlConnector();
        mySqlConnector.setUrl(this.mysqlUrl);
        // Set username, password, format, v.v...
        return mySqlConnector;
    }

    @Value("${tungds.mongodb.url}")
    String mongodbUrl;
    @Bean("mongodbConnector")
    DatabaseConnector mongodbConfigure() {
        DatabaseConnector mongoDbConnector = new MongoDbConnector();
        mongoDbConnector.setUrl(this.mongodbUrl);
        // Set username, password, format, v.v...
        return mongoDbConnector;
    }

    @Value("${tungds.postgresql.url}")
    String postgresqlUrl;
    @Bean("postgresqlConnector")
    DatabaseConnector postgresqlConfigure(){
        DatabaseConnector postgreSqlConnector = new PostgreSqlConnector();
        postgreSqlConnector.setUrl(this.postgresqlUrl);
        // Set username, password, format, v.v...
        return postgreSqlConnector;
    }

}
