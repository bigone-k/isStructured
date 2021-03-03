package group.bigone.api.global.config.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import group.bigone.api.global.config.database.h2.H2Connector;
import group.bigone.api.global.config.database.h2.H2DBConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**--------------------------------------------------------------------
 * ■PartnerDBConfig  ■2020. 11. 10.
 --------------------------------------------------------------------**/
@Configuration
@EnableTransactionManagement
public class MasterDBConfig {
    @Bean(name = "h2DBConfig")
    @ConfigurationProperties(prefix="h2info.master")
    public H2DBConfig h2DBConfig(){
        return new H2DBConfig();
    }

    @Bean(name = "masterH2DBConfig")
    @ConfigurationProperties(prefix="database.master")
    public HikariConfig masterH2DBConfig() {
        return new HikariConfig();
    }

    @Bean(name="masterDataSource")
    public DataSource masterDataSource() {
        H2Connector connector = new H2Connector(h2DBConfig());
        connector.RunServer();

        return new HikariDataSource(masterH2DBConfig());
    }

    @Bean(name="masterDBTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("masterDataSource") DataSource objDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(objDataSource);
        return transactionManager;
    }
}